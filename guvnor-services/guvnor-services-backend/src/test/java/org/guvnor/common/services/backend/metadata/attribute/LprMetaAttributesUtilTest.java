package org.guvnor.common.services.backend.metadata.attribute;

import java.util.HashMap;
import java.util.Map;

import org.guvnor.common.services.backend.metadata.LprMetaAttributesMock;
import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.junit.Test;

import static org.guvnor.common.services.shared.metadata.model.LprMetadataConsts.*;
import static org.junit.Assert.*;

/**
 * Created on 24-05-2017.
 */
public class LprMetaAttributesUtilTest {
    private final String TEST_ERROR_TEXT = "Test text";
    private final String TEST_RULE_GROUP = "LPR test rule group";

    @Test
    public void cleanupTest() {
        final Map<String, Object> testContent = new HashMap<String, Object>() {{
            put( RULE_TYPE, LprRuleType.REPORT_VALIDATION);
            put( REPORT_RECEIVED_FROM_DATE, 100L);
            put( REPORT_RECEIVED_TO_DATE, 100L);
            put( ENCOUNTER_START_FROM_DATE, 100L);
            put( ENCOUNTER_START_TO_DATE, 100L);
            put( ENCOUNTER_END_FROM_DATE, 100L);
            put( ENCOUNTER_END_TO_DATE, 100L);
            put( EPISODE_OF_CARE_START_FROM_DATE, 100L);
            put( EPISODE_OF_CARE_START_TO_DATE, 100L);
            put( ERROR_NUMBER, 300L);
            put( ERROR_TEXT, TEST_ERROR_TEXT);
            put( ERROR_TYPE, LprErrorType.FATAL);
            put( RULE_GROUP, TEST_RULE_GROUP);
            put( PRODUCTION_DATE, false);
            put( ARCHIVED_DATE, true);
            put( IS_VALID_FOR_LPR_REPORTS, true);
            put( IS_VALID_FOR_DUSAS_ABROAD_REPORTS, false);
            put( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS, false);
            put( IS_VALID_FOR_PRIVATE_SECTOR_REPORTS, true);
            put("SomeFunkyTestKey", "SomeFunkyTestValue");
        }};

        final Map<String, Object> returnedContent = LprMetaAttributesUtil.cleanup( testContent );

        assertNull( returnedContent.get( RULE_TYPE ));
        assertNull( returnedContent.get( REPORT_RECEIVED_FROM_DATE ));
        assertNull( returnedContent.get( REPORT_RECEIVED_TO_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_START_FROM_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_START_TO_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_END_FROM_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_END_TO_DATE ));
        assertNull( returnedContent.get( EPISODE_OF_CARE_START_FROM_DATE ));
        assertNull( returnedContent.get( EPISODE_OF_CARE_START_TO_DATE ));
        assertNull( returnedContent.get( ERROR_NUMBER ));
        assertNull( returnedContent.get( ERROR_TEXT ));
        assertNull( returnedContent.get( ERROR_TYPE ));
        assertNull( returnedContent.get( RULE_GROUP ));
        assertNull( returnedContent.get( PRODUCTION_DATE ));
        assertNull( returnedContent.get( ARCHIVED_DATE ));
        assertNull( returnedContent.get( IS_VALID_FOR_LPR_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_PRIVATE_SECTOR_REPORTS ));
        assertNotNull( returnedContent.get( "SomeFunkyTestKey" ) );
    }

    @Test
    public void toMapWildcardTest() {
        LprMetaAttributesMock mock = new LprMetaAttributesMock();
        final Map<String, Object> returnedContent = LprMetaAttributesUtil.toMap( mock, "*");
        assertEquals( mock.ruleType().getId(), returnedContent.get( RULE_TYPE ));
        assertEquals( mock.reportReceivedFromDate(), returnedContent.get( REPORT_RECEIVED_FROM_DATE ) );
        assertEquals( mock.reportReceivedToDate(), returnedContent.get( REPORT_RECEIVED_TO_DATE ) );
        assertEquals( mock.encounterStartFromDate(), returnedContent.get( ENCOUNTER_START_FROM_DATE ) );
        assertEquals( mock.encounterStartToDate(), returnedContent.get( ENCOUNTER_START_TO_DATE ) );
        assertEquals( mock.encounterEndFromDate(), returnedContent.get( ENCOUNTER_END_FROM_DATE ) );
        assertEquals( mock.encounterEndToDate(), returnedContent.get( ENCOUNTER_END_TO_DATE ) );
        assertEquals( mock.episodeOfCareStartFromDate(), returnedContent.get( EPISODE_OF_CARE_START_FROM_DATE ) );
        assertEquals( mock.episodeOfCareStartToDate(), returnedContent.get( EPISODE_OF_CARE_START_TO_DATE ) );
        assertEquals( mock.errorText(), returnedContent.get( ERROR_TEXT ));
        assertEquals( mock.errorType().getId(), returnedContent.get( ERROR_TYPE ) );
        assertEquals( mock.ruleGroup().getId(), returnedContent.get( RULE_GROUP ));
        assertEquals( mock.errorNumber().toString(), returnedContent.get( ERROR_NUMBER ) );
        assertEquals( mock.productionDate(), returnedContent.get( PRODUCTION_DATE ));
        assertEquals( mock.archivedDate(), returnedContent.get( ARCHIVED_DATE ));
        assertEquals( mock.isValidForLPRReports(), returnedContent.get( IS_VALID_FOR_LPR_REPORTS ));
        assertEquals( mock.isValidForDUSASSpecialityReports(), returnedContent.get( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ));
        assertEquals( mock.isValidForDUSASSpecialityReports(), returnedContent.get( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ));
        assertEquals( mock.isValidForPrivateSectorReports(), returnedContent.get( IS_VALID_FOR_PRIVATE_SECTOR_REPORTS ));
    }

    @Test
    public void toMapSpecificAttributesTest() {
        LprMetaAttributesMock mock = new LprMetaAttributesMock();
        final Map<String, Object> returnedContent = LprMetaAttributesUtil.toMap( mock, ERROR_TEXT, IS_VALID_FOR_LPR_REPORTS);
        assertNull( returnedContent.get( RULE_TYPE ));
        assertNull( returnedContent.get( REPORT_RECEIVED_FROM_DATE ));
        assertNull( returnedContent.get( REPORT_RECEIVED_TO_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_START_FROM_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_START_TO_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_END_FROM_DATE ));
        assertNull( returnedContent.get( ENCOUNTER_END_TO_DATE ));
        assertNull( returnedContent.get( EPISODE_OF_CARE_START_FROM_DATE ));
        assertNull( returnedContent.get( EPISODE_OF_CARE_START_TO_DATE ));
        assertNull( returnedContent.get( ERROR_NUMBER ));
        assertEquals( mock.errorText(), returnedContent.get( ERROR_TEXT ));
        assertNull( returnedContent.get( ERROR_TYPE ));
        assertNull( returnedContent.get( RULE_GROUP ));
        assertNull( returnedContent.get( PRODUCTION_DATE ));
        assertNull( returnedContent.get( ARCHIVED_DATE ));
        assertEquals( mock.isValidForLPRReports(), returnedContent.get( IS_VALID_FOR_LPR_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ));
        assertNull( returnedContent.get( IS_VALID_FOR_PRIVATE_SECTOR_REPORTS ));
    }
}
