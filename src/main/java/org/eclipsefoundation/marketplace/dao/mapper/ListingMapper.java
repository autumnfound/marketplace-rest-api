/*
 * Copyright (C) 2019 Eclipse Foundation and others.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
*/
package org.eclipsefoundation.marketplace.dao.mapper;

import static org.eclipsefoundation.marketplace.helper.SolrHelper.setField;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.eclipsefoundation.marketplace.helper.SolrHelper;
import org.eclipsefoundation.marketplace.model.Listing;
import org.eclipsefoundation.marketplace.namespace.SolrFieldNames;

/**
 * Mapping class for handling the transform between the Listing class and
 * SolrInputDocuments.
 * 
 * @author Martin Lowe
 */
public class ListingMapper implements SolrBeanMapper<Listing> {
	public static final ListingMapper INSTANCE = new ListingMapper();

	private ListingMapper() {
	}

	@Override
	public Listing toBean(SolrDocument doc) {
		Listing out = new Listing();

		out.setDocid((String) doc.getFieldValue(SolrFieldNames.DOCID));
		out.setListingId((long) doc.getFieldValue(SolrFieldNames.LISTING_ID));
		out.setInstallsRecent((long) doc.getFieldValue(SolrFieldNames.RECENT_NSTALLS));
		out.setInstallsTotal((long) doc.getFieldValue(SolrFieldNames.TOTAL_NSTALLS));
		out.setTitle((String) doc.getFieldValue(SolrFieldNames.LISTING_TITLE));
		out.setUrl((String) doc.getFieldValue(SolrFieldNames.LISTING_URL));
		out.setTeaser((String) doc.getFieldValue(SolrFieldNames.LISTING_TEASER));
		out.setBody((String) doc.getFieldValue(SolrFieldNames.LISTING_BODY));
		out.setFavoriteCount((long) doc.getFieldValue(SolrFieldNames.MARKETPLACE_FAVORITES));

		// check that it exists first, as non-existant fields throw exceptions
		if (doc.containsKey(SolrFieldNames.PLATFORMS)) {
			setField(doc.getFieldValue(SolrFieldNames.PLATFORMS), out::setPlatforms);
		}
		// check that it exists first, as non-existant fields throw exceptions
		if (doc.containsKey(SolrFieldNames.LICENSE_TYPE)) {
			setField(doc.getFieldValue(SolrFieldNames.LICENSE_TYPE), out::setLicenseType);
		}
		if (doc.containsKey(SolrFieldNames.INSTALLABLE_UNITS)) {
			setField(doc.getFieldValue(SolrFieldNames.INSTALLABLE_UNITS), out::setInstallableUnits);
		}
		return out;
	}

	@Override
	public SolrInputDocument toDocument(Listing document) {
		Map<String, SolrInputField> fields = new HashMap<>();
		SolrHelper.addInputField(SolrFieldNames.DOCID, document.getDocid(), fields);
		SolrHelper.addInputField(SolrFieldNames.LISTING_ID, document.getListingId(), fields);
		SolrHelper.addInputField(SolrFieldNames.RECENT_NSTALLS, document.getInstallsRecent(), fields);
		SolrHelper.addInputField(SolrFieldNames.TOTAL_NSTALLS, document.getInstallsTotal(), fields);
		SolrHelper.addInputField(SolrFieldNames.LISTING_TITLE, document.getTitle(), fields);
		SolrHelper.addInputField(SolrFieldNames.LISTING_URL, document.getUrl(), fields);
		SolrHelper.addInputField(SolrFieldNames.LISTING_BODY, document.getTeaser(), fields);
		SolrHelper.addInputField(SolrFieldNames.LISTING_TEASER, document.getBody(), fields);
		SolrHelper.addInputField(SolrFieldNames.MARKETPLACE_FAVORITES, document.getFavoriteCount(), fields);
		SolrHelper.addInputField(SolrFieldNames.LICENSE_TYPE, document.getLicenseType(), fields);
		SolrHelper.addInputField(SolrFieldNames.PLATFORMS, document.getPlatforms(), fields);
		SolrHelper.addInputField(SolrFieldNames.INSTALLABLE_UNITS, document.getInstallableUnits(), fields);

		return new SolrInputDocument(fields);
	}
}
