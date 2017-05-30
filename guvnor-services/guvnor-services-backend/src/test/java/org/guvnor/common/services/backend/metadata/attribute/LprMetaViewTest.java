package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.io.attribute.DublinCoreView;
import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.base.AbstractPath;
import org.uberfire.java.nio.base.AttrsStorage;
import org.uberfire.java.nio.base.BasicFileAttributesImpl;
import org.uberfire.java.nio.base.FileTimeImpl;
import org.uberfire.java.nio.file.*;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;
import org.uberfire.java.nio.file.attribute.UserPrincipalLookupService;
import org.uberfire.java.nio.file.spi.FileSystemProvider;
import org.uberfire.java.nio.fs.file.SimpleFileSystemProvider;
import org.uberfire.rpc.SessionInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Ascii.FS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by prc on 23-05-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LprMetaViewTest {
    private final String TEST_ERROR_TEXT = "Test text";
    private final String TEST_RULE_GROUP = "LPR test rule group";

    @Test
    public void testSimple() throws Exception {

        final Map<String, Object> testContent = new HashMap<String, Object>() {{
            put(LprMetaView.RULE_VALID_FROM_DATE, 100L);
            put(LprMetaView.RULE_VALID_TO_DATE, 200L);
            put(LprMetaView.ERROR_TYPE, LprErrorType.FATAL);
            put(LprMetaView.RULE_GROUP, TEST_RULE_GROUP);
            put(LprMetaView.ERROR_TEXT, TEST_ERROR_TEXT);
            put(LprMetaView.ERROR_NUMBER, 300L);
            put(LprMetaView.IN_PRODUCTION, false);
            put(LprMetaView.IS_DRAFT, true);
        }};

        AttrsStorage attrsStorage = mock(AttrsStorage.class);
        AbstractPath path = mock(AbstractPath.class);
        FileSystem fs = mock(FileSystem.class);
        FileSystemProvider fileSystemProvider = mock(FileSystemProvider.class);
        BasicFileAttributeView basicFileAttributeView = mock( BasicFileAttributeView.class);
        when( path.getAttrStorage() ).thenReturn( attrsStorage );
        when( attrsStorage.getContent()).thenReturn( testContent );
        when( path.getFileSystem() ).thenReturn( fs );
        when( fs.provider() ).thenReturn( fileSystemProvider );
        when( fileSystemProvider.getFileAttributeView( any( Path.class ), eq( BasicFileAttributeView.class ) )).thenReturn( basicFileAttributeView );
        when( basicFileAttributeView.readAttributes() ).thenReturn( new BasicFileAttributesImpl("", new FileTimeImpl(), new FileTimeImpl(), new FileTimeImpl(), 0L,false, false ));

        LprMetaView lprMetaView = new LprMetaView(path);

        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        LprErrorType errorType = lprMetaAttributes.errorType();

        assertEquals( LprRuleType.RuleType.NORMAL, lprMetaAttributes.Type()  );
        assertEquals( 100L, lprMetaAttributes.ruleValidFromDate().longValue() );
        assertEquals( 200L, lprMetaAttributes.ruleValidToDate().longValue() );
        assertEquals( true, lprMetaAttributes.isDraft()  );
        assertEquals( false, lprMetaAttributes.inProduction() );
        assertEquals( 300L, lprMetaAttributes.errorNumber().longValue() );
        assertEquals( this.TEST_ERROR_TEXT, lprMetaAttributes.errorText() );
        assertEquals( this.TEST_RULE_GROUP, lprMetaAttributes.ruleGroup()  );
        assertEquals( LprErrorType.FATAL, lprMetaAttributes.errorType() );
    }
}