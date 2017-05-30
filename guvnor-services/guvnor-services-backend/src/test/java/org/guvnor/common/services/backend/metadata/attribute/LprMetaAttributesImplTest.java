package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.java.nio.base.BasicFileAttributesImpl;
import org.uberfire.java.nio.base.FileTimeImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by prc on 23-05-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LprMetaAttributesImplTest {
    private final String TEST_ERROR_TEXT = "Test text";
    private final String TEST_RULE_GROUP = "LPR test rule group";

    @Test
    public void testSimple() throws Exception {
        LprMetaAttributesImpl lprMetaAttributesImpl = new LprMetaAttributesImpl( new BasicFileAttributesImpl("", new FileTimeImpl( 100 ), new FileTimeImpl( 200 ), new FileTimeImpl( 300 ), 0L,false, false ) );

        assertEquals( 100L, lprMetaAttributesImpl.lastModifiedTime().toMillis()  );
        assertEquals( 200L, lprMetaAttributesImpl.creationTime().toMillis()  );
        assertEquals( 300L, lprMetaAttributesImpl.lastAccessTime().toMillis()  );
        assertEquals( false, lprMetaAttributesImpl.isRegularFile()  );
        assertEquals( false, lprMetaAttributesImpl.isDirectory() );
        assertEquals( false, lprMetaAttributesImpl.isSymbolicLink() );
        assertEquals( false, lprMetaAttributesImpl.isOther() );
        assertEquals( 0L, lprMetaAttributesImpl.size()  );
        assertEquals( "", lprMetaAttributesImpl.fileKey().toString() );
    }

    @Test
    public void testCreateFromMetadata() throws Exception {
        Metadata metadata = mock( Metadata.class );


        when( metadata.getLprRuleType() ).thenReturn( LprRuleType.RuleType.NORMAL  );
        when( metadata.getRuleValidFromDate() ).thenReturn( 100L ) ;
        when( metadata.getRuleValidToDate() ).thenReturn( 200L );
        when( metadata.isDraft() ).thenReturn( true );
        when( metadata.isInProduction() ).thenReturn( true );
        when( metadata.getErrorNumber() ).thenReturn( 911L );
        when( metadata.getErrorText() ).thenReturn( TEST_ERROR_TEXT );
        when( metadata.getRuleGroup() ).thenReturn( TEST_RULE_GROUP );
        when( metadata.getErrorType() ).thenReturn( LprErrorType.WARNING );

        LprMetaAttributesImpl lprMetaAttributesImpl = new LprMetaAttributesImpl( metadata );

        assertEquals( LprRuleType.RuleType.NORMAL, lprMetaAttributesImpl.getType()  );
        assertEquals( 100L, lprMetaAttributesImpl.ruleValidFromDate().longValue() );
        assertEquals( 200L, lprMetaAttributesImpl.ruleValidToDate().longValue() );
        assertEquals( true, lprMetaAttributesImpl.isDraft()  );
        assertEquals( true, lprMetaAttributesImpl.isInProduction() );
        assertEquals( 911L, lprMetaAttributesImpl.errorNumber().longValue() );
        assertEquals( this.TEST_ERROR_TEXT, lprMetaAttributesImpl.errorText() );
        assertEquals( this.TEST_RULE_GROUP, lprMetaAttributesImpl.ruleGroup()  );
        assertEquals( LprErrorType.WARNING, lprMetaAttributesImpl.errorType() );
    }
}
