package org.guvnor.common.services.shared.metadata.model;

/**
 * Created on 15-06-2017.
 */
public class LprMetadataConsts {
    public static final String LPRMETA = "lprmeta";

    // IF YOU ADD NEW METADATA THEN REMEMBER TO UPDATE THE VARIOUS EDITORS THAT SAVES METADATA TO DRL FILE (example: DRLEditorPresenter#save)
    public static final String RULE_TYPE = LPRMETA + ".type";
    public static final String ERROR_TYPE = LPRMETA + ".errorType";
    public static final String RULE_GROUP = LPRMETA + ".ruleGroup";
    public static final String ERROR_TEXT = LPRMETA + ".errorText";
    public static final String ERROR_NUMBER = LPRMETA + ".errorNumber";
    public static final String IS_VALID_FOR_LPR_REPORTS = LPRMETA + ".isValidForLPRReports";
    public static final String IS_VALID_FOR_DUSAS_ABROAD_REPORTS = LPRMETA + ".isValidForDUSASAbroadReports";
    public static final String IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS = LPRMETA + ".isValidForDUSASSpecialityReports";
    public static final String IS_VALID_FOR_PRIVATE_SECTOR_REPORTS = LPRMETA + ".isValidForPrivateSectorReports";
    public static final String HAS_PROD_VERSION = LPRMETA + ".hasProdVersion";
    public static final String PRODUCTION_DATE = LPRMETA + ".productionDate";
    public static final String ARCHIVED_DATE = LPRMETA + ".archivedDate";
    public static final String REPORT_RECEIVED_FROM_DATE = LPRMETA + ".reportReceivedFromDate";
    public static final String REPORT_RECEIVED_TO_DATE = LPRMETA + ".reportReceivedToDate";
    public static final String ENCOUNTER_START_FROM_DATE = LPRMETA + ".encounterStartFromDate";
    public static final String ENCOUNTER_START_TO_DATE = LPRMETA + ".encounterStartToDate";
    public static final String ENCOUNTER_END_FROM_DATE = LPRMETA + ".encounterEndFromDate";
    public static final String ENCOUNTER_END_TO_DATE = LPRMETA + ".encounterEndToDate";
    public static final String EPISODE_OF_CARE_START_FROM_DATE = LPRMETA + ".episodeOfCareStartFromDate";
    public static final String EPISODE_OF_CARE_START_TO_DATE = LPRMETA + ".episodeOfCareStartToDate";

/*
    public static final ArrayList<String> ALL_METADATA = new ArrayList<String>() {{
        add( RULE_TYPE );
        add( ERROR_TYPE );
        add( RULE_GROUP );
        add( ERROR_TEXT );
        add( ERROR_NUMBER );
        add( IS_VALID_FOR_LPR_REPORTS );
        add( IS_VALID_FOR_DUSAS_ABROAD_REPORTS );
        add( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS );
        add( IS_VALID_FOR_PRIVATE_SECTOR_REPORTS );
        add( PRODUCTION_DATE );
        add( HAS_PROD_VERSION );
        add( ARCHIVED_DATE );
        add( REPORT_RECEIVED_FROM_DATE );
        add( REPORT_RECEIVED_TO_DATE );
        add( ENCOUNTER_START_FROM_DATE );
        add( ENCOUNTER_START_TO_DATE );
        add( ENCOUNTER_END_FROM_DATE );
        add( ENCOUNTER_END_TO_DATE );
        add( EPISODE_OF_CARE_START_FROM_DATE );
        add( EPISODE_OF_CARE_START_TO_DATE );
    }};
*/

    //Pseudo meta data that is not persisted, but only used to describe the search criteria for SearchService
    public static final String SEARCH_IS_PRODUCTION = LPRMETA + ".searchIsProduction";
    public static final String SEARCH_IS_DRAFT = LPRMETA + ".searchIsDraft";
    public static final String SEARCH_IS_ARCHIVED = LPRMETA + ".searchIsArchived";
    public static final String SEARCH_REPORT_RECEIVED_DATE = LPRMETA + ".searchReportReceivedDate";
    public static final String SEARCH_ENCOUNTER_START_DATE = LPRMETA + ".searchEncounterStartDate";
    public static final String SEARCH_ENCOUNTER_END_DATE = LPRMETA + ".searchEncounterEndDate";
    public static final String SEARCH_EPISODE_OF_CARE_START_DATE = LPRMETA + ".searchEpisodeOfCareStartDate";
}
