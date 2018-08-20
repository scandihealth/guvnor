package org.guvnor.common.services.backend.metadata.attribute;

import java.util.HashMap;
import java.util.Map;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleGroup;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;

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
                    String ruleTypeId = attrs.ruleType() != null ? attrs.ruleType().getId() : LprRuleType.NOT_LPR.getId();
                    put( RULE_TYPE, ruleTypeId ); //save id instead of enum name to make search work
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
                if ( attribute.equals( "*" ) || attribute.equals( ARCHIVED_DATE ) ) {
                    put( ARCHIVED_DATE, attrs.archivedDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( PRODUCTION_DATE ) ) {
                    put( PRODUCTION_DATE, attrs.productionDate() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( HAS_PROD_VERSION ) ) {
                    put( HAS_PROD_VERSION, attrs.hasProdVersion() );
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
                if ( attribute.equals( "*" ) || attribute.equals( IS_VALID_FOR_PRIMARY_SECTOR_REPORTS ) ) {
                    put( IS_VALID_FOR_PRIMARY_SECTOR_REPORTS, attrs.isValidForPrimarySectorReports() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_NUMBER ) ) {
                    //errorNumber is stored as string to make wild card searches possible
                    put( ERROR_NUMBER, attrs.errorNumber() != null ? attrs.errorNumber().toString() : "0" );
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_TEXT ) ) {
                    put( ERROR_TEXT, attrs.errorText() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( WARNING_TIME_LIMIT ) ) {
                    put( WARNING_TIME_LIMIT, attrs.warningTimeLimit() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( RULE_GROUP ) ) {
                    String ruleGroupId = attrs.ruleGroup() != null ? attrs.ruleGroup().getId() : LprRuleGroup.NONE.getId();
                    put( RULE_GROUP, ruleGroupId ); //save id instead of enum name to make search work
                }
                if ( attribute.equals( "*" ) || attribute.equals( ERROR_TYPE ) ) {
                    String errorTypeId = attrs.errorType() != null ? attrs.errorType().getId() : LprErrorType.OK.getId();
                    put( ERROR_TYPE, errorTypeId ); //save id instead of enum name to make search work
                }

                if ( attribute.equals( "*" ) ) {
                    break;
                }
            }
        }};
    }
}
