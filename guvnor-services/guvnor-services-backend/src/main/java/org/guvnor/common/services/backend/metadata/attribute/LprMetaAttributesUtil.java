package org.guvnor.common.services.backend.metadata.attribute;

import java.util.HashMap;
import java.util.Map;

import static org.guvnor.common.services.shared.metadata.model.LprMetadataConsts.*;
import static org.uberfire.commons.validation.PortablePreconditions.*;

/**
 * Created on 16-02-2017.
 */
public class LprMetaAttributesUtil {

    public static Map<String, Object> cleanup( final Map<String, Object> _attrs ) {
        final Map<String, Object> attrs = new HashMap<String, Object>( _attrs );

        for ( final String key : _attrs.keySet() ) {
            if ( key.startsWith( LPRMETA ) ) {
                attrs.put( key, null );
            }
        }
        return attrs;
    }

    public static Map<String, Object> toMap( final LprMetaAttributes attrs, final String... attributes ) {
        return new HashMap<String, Object>() {{
            for ( final String attribute : attributes ) {
                checkNotEmpty( "attribute", attribute );

                if ( attribute.equals( "*" ) || attribute.equals( RULE_TYPE ) ) {
                    put( RULE_TYPE, attrs.ruleType().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( REPORT_RECEIVED_FROM_DATE ) ) {
                    put( REPORT_RECEIVED_FROM_DATE, attrs.reportReceivedFromDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( REPORT_RECEIVED_TO_DATE ) ) {
                    put( REPORT_RECEIVED_TO_DATE, attrs.reportReceivedToDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ENCOUNTER_START_FROM_DATE ) ) {
                    put( ENCOUNTER_START_FROM_DATE, attrs.encounterStartFromDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ENCOUNTER_START_TO_DATE ) ) {
                    put( ENCOUNTER_START_TO_DATE, attrs.encounterStartToDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ENCOUNTER_END_FROM_DATE ) ) {
                    put( ENCOUNTER_END_FROM_DATE, attrs.encounterEndFromDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ENCOUNTER_END_TO_DATE ) ) {
                    put( ENCOUNTER_END_TO_DATE, attrs.encounterEndToDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( EPISODE_OF_CARE_START_FROM_DATE ) ) {
                    put( EPISODE_OF_CARE_START_FROM_DATE, attrs.episodeOfCareStartFromDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( EPISODE_OF_CARE_START_TO_DATE ) ) {
                    put( EPISODE_OF_CARE_START_TO_DATE, attrs.episodeOfCareStartToDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( IS_DRAFT ) ) {
                    put( IS_DRAFT, attrs.isDraft() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( IN_PRODUCTION ) ) {
                    put( IN_PRODUCTION, attrs.inProduction() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( IS_VALID_FOR_LPR_REPORTS ) ) {
                    put( IS_VALID_FOR_LPR_REPORTS, attrs.isValidForLPRReports() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ) ) {
                    put( IS_VALID_FOR_DUSAS_ABROAD_REPORTS, attrs.isValidForDUSASAbroadReports() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ) ) {
                    put( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS, attrs.isValidForDUSASSpecialityReports() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_NUMBER ) ) {
                    //errorNumber is stored as string to make wild card searches possible
                    put( ERROR_NUMBER, attrs.errorNumber().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_TEXT ) ) {
                    put( ERROR_TEXT, attrs.errorText() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( RULE_GROUP ) ) {
                    put( RULE_GROUP, attrs.ruleGroup() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_TYPE ) ) {
                    put( ERROR_TYPE, attrs.errorType().toString() );
                }

                if ( attribute.equals( "*" ) ) {
                    break;
                }
            }
        }};
    }
}
