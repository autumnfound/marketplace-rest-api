const axios = require('axios');
const instance = axios.create({
  timeout: 1000,
  headers: {'User-Agent': 'mpc/0.0.0'}
});
const randomWords = require('random-words');
const uuid = require('uuid');
const argv = require('yargs')
  .option('c', {
    description: 'Number of listings to generate',
    alias: 'count',
    default: 1000,
    nargs: 1
  })
  .option('i', {
    description: 'Number of installs to generate',
    alias: 'installs',
    default: 0,
    nargs: 1
  })
  .option('s', {
    description: 'Server address (e.g. http://localhost:8090)',
    alias: 'server',
    nargs: 1,
    demandOption: true
  }).argv;

let max = argv.c;
var moment = require('moment-timezone');
const lic_types = ["EPL-2.0","EPL-1.0","GPL"];
const platforms = ["windows","macos","linux"];
const eclipseVs = ["4.6","4.7","4.8","4.9","4.10","4.11","4.12"];
const javaVs = ["1.5", "1.6", "1.7", "1.8", "1.9", "1.10"];
const categoryIds = [];
for (var i=0;i<200;i++) {
  categoryIds.push(uuid.v4());
}
const marketIds = [];
for (var i=0;i<5;i++) {
  marketIds.push(uuid.v4());
}

createListing(0);
createCategory(0);
createMarket(0);

function shuff(arr) {
  var out = Array.from(arr);
  for (var i=0;i<out.length;i++) {
    var curr = out[i];
    var rndIdx = Math.floor(Math.random() * arr.length);
    var next = out[rndIdx];
    out[rndIdx] = curr;
    out[i] = next;
  }
  return out;
}

function splice(arr) {
  var out = [];
  var copy = shuff(arr);
  var c = Math.floor(Math.random() * arr.length - 1) + 1;
  for (var i=0; i<=c;i++) {
    out.push(copy[i]);
  }
  return out;
}

function createListing(count) {
  if (count >= max) {
    return;
  }
  
  console.log(`Generating listing ${count} of ${max}`);
  var json = generateJSON(uuid.v4());
  instance.put(argv.s+"/listings/", json)
    .then(() => {
      var installs = Math.floor(Math.random()*argv.i);
      console.log(`Generating ${installs} install records for listing '${json.id}'`);
      createInstall(0, installs, json, () => createListing(count+1));
    })
    .catch(err => console.log(err));
}

function createCategory(count) {
  if (count >= categoryIds.length) {
    return;
  }

  instance.put(argv.s+"/categories/", generateCategoryJSON(categoryIds[count++]))
    .then(() => createCategory(count))
    .catch(err => console.log(err));
}

function createMarket(count) {
  if (count >= marketIds.length) {
    return;
  }

  instance.put(argv.s+"/markets/", generateMarketJSON(marketIds[count++]))
    .then(() => createMarket(count))
    .catch(err => console.log(err));
}

function createInstall(curr, max, listing, callback) {
  if (curr >= max) {
    return callback();
  }
  var json = generateInstallJSON(listing);
  instance.post(`${argv.s}/installs/${json['listing_id']}/${json.version}`, json)
    .then(createInstall(curr+1,max,listing,callback))
    .catch(err => console.log(err));
}

function generateJSON(id) {
  var solutions = [];
  var solsCount = Math.floor(Math.random()*5) + 1;
  for (var i=0; i < solsCount; i++) {
    solutions.push({
      "version": i,
      "eclipse_versions": splice(eclipseVs),
      "min_java_version": javaVs[Math.floor(Math.random()*javaVs.length)],
      "platforms": splice(platforms)
    });
  }
  
  return {
    "id": id,
  	"title": "Sample",
  	"url": "https://jakarta.ee",
  	"foundation_member": false,
  	"teaser": randomWords({exactly:1, wordsPerString:Math.floor(Math.random()*100)})[0],
  	"body": randomWords({exactly:1, wordsPerString:Math.floor(Math.random()*300)})[0],
    "status": "draft",
  	"support_url": "https://jakarta.ee/about/faq",
  	"license_type": lic_types[Math.floor(Math.random()*lic_types.length)],
    "created": moment.tz((new Date()).toISOString(), "America/Toronto").format(),
    "changed": moment.tz((new Date()).toISOString(), "America/Toronto").format(),
  	"authors": [
  		{
  			"full_name": "Martin Lowe",
  	    "username": "autumnfound"
  		}
  	],
    "organization": {
			"name": "Eclipse Foundation",
			"id": 1
		},
  	"tags": [
  		{
  			"name": "Build tools",
  			"id": "1",
  			"url": ""
  		}
  	],
  	"versions": solutions,
	"market_ids": splice(marketIds).splice(0,Math.ceil(Math.random()*2)),
  "category_ids": splice(categoryIds).splice(0,Math.ceil(Math.random()*5)+1),
	"screenshots": ["http://www.example.com/img/sample.png"]
  };
}

function generateCategoryJSON(id) {
  return {
    "id": id,
    "name": randomWords({exactly:1, wordsPerString:Math.ceil(Math.random()*4)})[0],
    "url": "https://www.eclipse.org"
  };
}

function generateMarketJSON(id) {
  return {
    "id": id,
    "name": randomWords({exactly:1, wordsPerString:Math.ceil(Math.random()*4)})[0],
    "url": "https://www.eclipse.org"
  };
}

function generateInstallJSON(listing) {
  var version = listing.versions[Math.floor(Math.random()*listing.versions.length)];
  var javaVersions = Array.from(javaVs).splice(javaVs.indexOf(version["min_java_version"]));
  var eclipseVersions = Array.from(eclipseVs).splice(eclipseVs.indexOf(version["eclipse_version"]));
  
  return {
    "listing_id": listing.id,
    "version": version.version,
    "java_version": shuff(javaVersions)[0],
    "os": shuff(version.platforms)[0],
    "eclipse_version": shuff(eclipseVersions)[0]
  };
}
