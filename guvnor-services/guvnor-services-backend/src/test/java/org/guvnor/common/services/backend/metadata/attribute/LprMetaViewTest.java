package org.guvnor.common.services.backend.metadata.attribute;

import java.util.HashMap;
import java.util.Map;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.java.nio.base.AbstractPath;
import org.uberfire.java.nio.base.AttrsStorage;
import org.uberfire.java.nio.base.BasicFileAttributesImpl;
import org.uberfire.java.nio.base.FileTimeImpl;
import org.uberfire.java.nio.file.FileSystem;
import org.uberfire.java.nio.file.Path;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.spi.FileSystemProvider;

import static org.guvnor.common.services.shared.metadata.model.LprMetadataConsts.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created on 23-05-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LprMetaViewTest {
    private final String TEST_ERROR_TEXT = "Test text";
    private final String TEST_RULE_GROUP = "LPR test rule group";

    @Test
    public void testSimple() throws Exception {

        final Map<String, Object> mockAttrs = new HashMap<String, Object>() {{
            put( RULE_TYPE, LprRuleType.REPORT_VALIDATION.toString());
            put( REPORT_RECEIVED_FROM_DATE, 100L);
            put( REPORT_RECEIVED_TO_DATE, 100L);
            put( ENCOUNTER_START_FROM_DATE, 100L);
            put( ENCOUNTER_START_TO_DATE, 100L);
            put( ENCOUNTER_END_FROM_DATE, 100L);
            put( ENCOUNTER_END_TO_DATE, 100L);
            put( EPISODE_OF_CARE_START_FROM_DATE, 100L);
            put( EPISODE_OF_CARE_START_TO_DATE, 100L);
            put( ERROR_NUMBER, "300");
            put( ERROR_TEXT, TEST_ERROR_TEXT);
            put( ERROR_TYPE, LprErrorType.FATAL.toString());
            put( RULE_GROUP, TEST_RULE_GROUP);
            put( IN_PRODUCTION, false);
            put( IS_DRAFT, true);
            put( IS_VALID_FOR_LPR_REPORTS, true);
            put( IS_VALID_FOR_DUSAS_ABROAD_REPORTS, false);
            put( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS, false);
        }};

        AttrsStorage attrsStorage = mock(AttrsStorage.class);
        AbstractPath path = mock(AbstractPath.class);
        FileSystem fs = mock(FileSystem.class);
        FileSystemProvider fileSystemProvider = mock(FileSystemProvider.class);
        BasicFileAttributeView basicFileAttributeView = mock( BasicFileAttributeView.class);
        when( path.getAttrStorage() ).thenReturn( attrsStorage );
        when( attrsStorage.getContent()).thenReturn( mockAttrs );
        when( path.getFileSystem() ).thenReturn( fs );
        when( fs.provider() ).thenReturn( fileSystemProvider );
        when( fileSystemProvider.getFileAttributeView( any( Path.class ), eq( BasicFileAttributeView.class ) )).thenReturn( basicFileAttributeView );
        when( basicFileAttributeView.readAttributes() ).thenReturn( new BasicFileAttributesImpl("", new FileTimeImpl(), new FileTimeImpl(), new FileTimeImpl(), 0L,false, false ));

        LprMetaView lprMetaView = new LprMetaView(path);
        LprMetaAttributes returnedAttrs = lprMetaView.readAttributes();

        assertEquals( mockAttrs.get( RULE_TYPE ), returnedAttrs.ruleType().toString() );
        assertEquals( mockAttrs.get( REPORT_RECEIVED_FROM_DATE ), returnedAttrs.reportReceivedFromDate() );
        assertEquals( mockAttrs.get( REPORT_RECEIVED_TO_DATE ), returnedAttrs.reportReceivedToDate() );
        assertEquals( mockAttrs.get( ENCOUNTER_START_FROM_DATE ), returnedAttrs.encounterStartFromDate() );
        assertEquals( mockAttrs.get( ENCOUNTER_START_TO_DATE ), returnedAttrs.encounterStartToDate() );
        assertEquals( mockAttrs.get( ENCOUNTER_END_FROM_DATE ), returnedAttrs.encounterEndFromDate() );
        assertEquals( mockAttrs.get( ENCOUNTER_END_TO_DATE ), returnedAttrs.encounterEndToDate() );
        assertEquals( mockAttrs.get( EPISODE_OF_CARE_START_FROM_DATE ), returnedAttrs.episodeOfCareStartFromDate() );
        assertEquals( mockAttrs.get( EPISODE_OF_CARE_START_TO_DATE ), returnedAttrs.episodeOfCareStartToDate() );
        assertEquals( mockAttrs.get( ERROR_TEXT ), returnedAttrs.errorText() );
        assertEquals( mockAttrs.get( ERROR_TYPE ), returnedAttrs.errorType().toString() );
        assertEquals( mockAttrs.get( RULE_GROUP ), returnedAttrs.ruleGroup() );
        assertEquals( mockAttrs.get( ERROR_NUMBER ), returnedAttrs.errorNumber().toString() );
        assertEquals( mockAttrs.get( IN_PRODUCTION ), returnedAttrs.inProduction() );
        assertEquals( mockAttrs.get( IS_DRAFT ), returnedAttrs.isDraft() );
        assertEquals( mockAttrs.get( IS_VALID_FOR_LPR_REPORTS ), returnedAttrs.isValidForLPRReports() );
        assertEquals( mockAttrs.get( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ), returnedAttrs.isValidForDUSASSpecialityReports() );
        assertEquals( mockAttrs.get( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ), returnedAttrs.isValidForDUSASSpecialityReports() );
    }
}