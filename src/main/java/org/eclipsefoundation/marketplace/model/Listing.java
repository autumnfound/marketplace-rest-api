/*
 * Copyright (C) 2019 Eclipse Foundation and others.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
*/
package org.eclipsefoundation.marketplace.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Listing extends SolrBean {

	private long listingId;
	private String title;
	private String url;
	private String teaser;
	private String body;
	private long installsTotal;
	private long installsRecent;
	private long favoriteCount;
	private List<String> licenseType;
	private List<String> platforms;
	private List<String> installableUnits;

	public Listing() {
		this.platforms = new ArrayList<>();
		this.licenseType = new ArrayList<>();
		this.installableUnits = new ArrayList<>();
	}

	/**
	 * @return the listingId
	 */
	public long getListingId() {
		return listingId;
	}

	/**
	 * @param listingId the id to set
	 */
	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the teaser
	 */
	public String getTeaser() {
		return teaser;
	}

	/**
	 * @param teaser the teaser to set
	 */
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the installsTotal
	 */
	public long getInstallsTotal() {
		return installsTotal;
	}

	/**
	 * @param installsTotal the installsTotal to set
	 */
	public void setInstallsTotal(long installsTotal) {
		this.installsTotal = installsTotal;
	}

	/**
	 * @return the installsRecent
	 */
	public long getInstallsRecent() {
		return installsRecent;
	}

	/**
	 * @param installsRecent the installsRecent to set
	 */
	public void setInstallsRecent(long installsRecent) {
		this.installsRecent = installsRecent;
	}

	/**
	 * @return the favoriteCount
	 */
	public long getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * @return the licenseType
	 */
	public List<String> getLicenseType() {
		return new ArrayList<>(licenseType);
	}

	/**
	 * @param licenseType the licenseType to set
	 */
	public void setLicenseType(List<String> licenseType) {
		this.licenseType = new ArrayList<>(licenseType);
	}

	/**
	 * @return the platforms
	 */
	public List<String> getPlatforms() {
		return new ArrayList<>(platforms);
	}

	/**
	 * @param platforms the platforms to set
	 */
	public void setPlatforms(List<String> platforms) {
		Objects.requireNonNull(platforms);
		this.platforms = new ArrayList<>(platforms);
	}

	/**
	 * @return the installableUnits
	 */
	public List<String> getInstallableUnits() {
		return new ArrayList<>(installableUnits);
	}

	/**
	 * @param installableUnits the installableUnits to set
	 */
	public void setInstallableUnits(List<String> installableUnits) {
		Objects.requireNonNull(installableUnits);
		this.installableUnits = new ArrayList<>(installableUnits);
	}

}