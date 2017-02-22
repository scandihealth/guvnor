package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.DiscussionRecord;

import java.util.HashMap;
import java.util.Map;

import static org.uberfire.commons.validation.PortablePreconditions.checkNotEmpty;

/**
 * Created by prc on 16-02-2017.
 */
public class LprMetaAttributesUtil {

    public static Map<String, Object> cleanup( final Map<String, Object> _attrs ) {
        final Map<String, Object> attrs = new HashMap<String, Object>( _attrs );

        for ( final String key : _attrs.keySet() ) {
            if ( key.startsWith( LprMetaView.TYPE ) || key.startsWith( LprMetaView.VALID_FROM ) ||
                    key.startsWith( LprMetaView.VALID_TO ) || key.startsWith( LprMetaView.IS_DRAFT ) ||
                    key.startsWith( LprMetaView.IN_PRODUCTION )  ) {
                attrs.put( key, null );
            }
        }

        return attrs;
    }

    public static Map<String, Object> toMap(final LprMetaAttributes attrs,
                                            final String... attributes ) {
        return new HashMap<String, Object>() {{
            for ( final String attribute : attributes ) {
                checkNotEmpty( "attribute", attribute );

                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.TYPE ) ) {
                    put( LprMetaView.TYPE, attrs.Type() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.VALID_FROM ) ) {
                    put( LprMetaView.VALID_FROM, attrs.ValidFrom().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.VALID_TO ) ) {
                    put( LprMetaView.VALID_TO, attrs.ValidTo().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.IS_DRAFT ) ) {
                    put( LprMetaView.IS_DRAFT, attrs.isDraft() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.IN_PRODUCTION ) ) {
                    put( LprMetaView.IN_PRODUCTION, attrs.inProduction() );
                }

                if ( attribute.equals( "*" ) ) {
                    break;
                }
            }
        }};
    }

    private static String buildAttrName( final String title,
                                         final int i ) {
        return title + "[" + i + "]";
    }
}
