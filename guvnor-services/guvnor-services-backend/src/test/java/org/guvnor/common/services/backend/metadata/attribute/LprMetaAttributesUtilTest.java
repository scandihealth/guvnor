package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.backend.metadata.LprMetaAttributesMock;
import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by prc on 24-05-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LprMetaAttributesUtilTest {
    private final String TEST_ERROR_TEXT = "Test text";
    private final String TEST_RULE_GROUP = "LPR test rule group";

    @Test
    public void cleanupTest() {
        final Map<String, Object> testContent = new HashMap<String, Object>() {{
            put(LprMetaView.RULE_VALID_FROM_DATE, 100L);
            put(LprMetaView.RULE_VALID_TO_DATE, 200L);
            put(LprMetaView.ERROR_TYPE, LprErrorType.FATAL);
            put(LprMetaView.RULE_GROUP, TEST_RULE_GROUP);
            put(LprMetaView.ERROR_TEXT, TEST_ERROR_TEXT);
            put(LprMetaView.ERROR_NUMBER, 300L);
            put(LprMetaView.IN_PRODUCTION, false);
            put(LprMetaView.IS_DRAFT, true);
            put("SomeFunkyTestKey", "SomeFunkyTestValue");
        }};

        final Map<String, Object> returnedContent = LprMetaAttributesUtil.cleanup( testContent );

        assertNull( returnedContent.get( LprMetaView.ERROR_TEXT ));
        assertNull( returnedContent.get( LprMetaView.RULE_VALID_FROM_DATE ));
        assertNull( returnedContent.get( LprMetaView.RULE_VALID_TO_DATE ));
        assertNull( returnedContent.get( LprMetaView.ERROR_TYPE ));
        assertNull( returnedContent.get( LprMetaView.RULE_GROUP ));
        assertNull( returnedContent.get( LprMetaView.ERROR_TEXT ));;
        assertNull( returnedContent.get( LprMetaView.ERROR_NUMBER ));
        assertNull( returnedContent.get( LprMetaView.IN_PRODUCTION ));
        assertNull( returnedContent.get( LprMetaView.IS_DRAFT ));
        assertNotNull( returnedContent.get( "SomeFunkyTestKey" ) );
    }

    @Test
    public void toMapTest() {
        final Map<String, Object> returnedContent = LprMetaAttributesUtil.toMap( new LprMetaAttributesMock(), "*");

        assertEquals( LprMetaAttributesMock.TEST_ERROR_TEXT, returnedContent.get( LprMetaView.ERROR_TEXT ));
        assertEquals( 100L, Long.parseLong( returnedContent.get( LprMetaView.RULE_VALID_FROM_DATE ).toString() ) );
        assertEquals( 200L, Long.parseLong( returnedContent.get( LprMetaView.RULE_VALID_TO_DATE ).toString() ) );
        assertEquals( LprErrorType.ERROR, LprErrorType.valueOf( returnedContent.get( LprMetaView.ERROR_TYPE ).toString() ) );
        assertEquals( LprMetaAttributesMock.TEST_RULE_GROUP, returnedContent.get( LprMetaView.RULE_GROUP ));
        assertEquals( 300L, Long.parseLong( returnedContent.get( LprMetaView.ERROR_NUMBER ).toString() ) );
        assertEquals( false, returnedContent.get( LprMetaView.IN_PRODUCTION ));
        assertEquals( true, returnedContent.get( LprMetaView.IS_DRAFT ));

    }
}
