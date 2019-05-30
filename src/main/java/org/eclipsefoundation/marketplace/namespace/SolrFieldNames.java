/*
 * Copyright (C) 2019 Eclipse Foundation and others.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
*/
package org.eclipsefoundation.marketplace.namespace;

public final class SolrFieldNames {

	public static final String DOCID = "id";
	public static final String LISTING_ID = "entity_id";
	public static final String LISTING_TITLE = "label";
	public static final String LISTING_URL = "url";
	public static final String LISTING_TEASER = "teaser";
	public static final String LISTING_BODY = "content";
	public static final String RECENT_NSTALLS = "itm_field_installs_recent";
	public static final String TOTAL_NSTALLS = "itm_field_installs_total";
	public static final String MARKETPLACE_FAVORITES = "its_field_mpc_favorite_count";
	public static final String LICENSE_TYPE = "sm_field_licensetype";
	public static final String PLATFORMS = "sm_field_platform";
	public static final String INSTALLABLE_UNITS = "sm_field_installable_units";

	private SolrFieldNames() {
	}
}
