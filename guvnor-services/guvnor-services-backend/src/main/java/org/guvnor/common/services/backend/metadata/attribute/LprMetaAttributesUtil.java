package org.guvnor.common.services.backend.metadata.attribute;

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
            if ( key.startsWith( LprMetaView.TYPE ) ||
                    key.startsWith( LprMetaView.RECIEVED_VALID_FROM_DATE) ||
                    key.startsWith( LprMetaView.RECIEVED_VALID_TO_DATE) ||
                    key.startsWith( LprMetaView.IS_DRAFT ) ||
                    key.startsWith( LprMetaView.IN_PRODUCTION )  ||
                    key.startsWith( LprMetaView.ERROR_NUMBER )  ||
                    key.startsWith( LprMetaView.ERROR_TEXT )  ||
                    key.startsWith( LprMetaView.RULE_GROUP )  ||
                    key.startsWith( LprMetaView.ERROR_TYPE ) ) {
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
                    put( LprMetaView.TYPE, attrs.Type().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.RECIEVED_VALID_FROM_DATE) ) {
                    put( LprMetaView.RECIEVED_VALID_FROM_DATE, attrs.recievedValidFromDate().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.RECIEVED_VALID_TO_DATE) ) {
                    put( LprMetaView.RECIEVED_VALID_TO_DATE, attrs.recievedValidToDate().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.IS_DRAFT ) ) {
                    put( LprMetaView.IS_DRAFT, attrs.isDraft() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.IN_PRODUCTION ) ) {
                    put( LprMetaView.IN_PRODUCTION, attrs.inProduction() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.ERROR_NUMBER ) ) {
                    if(attrs.errorNumber() > 0)
                        put( LprMetaView.ERROR_NUMBER, attrs.errorNumber().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.ERROR_TEXT ) ) {
                    put( LprMetaView.ERROR_TEXT, attrs.errorText() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.RULE_GROUP ) ) {
                    put( LprMetaView.RULE_GROUP, attrs.ruleGroup().toString() );
                }
                if ( attribute.equals( "*" ) || attribute.equals( LprMetaView.ERROR_TYPE ) ) {
                    put( LprMetaView.ERROR_TYPE, attrs.errorType().toString() );
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
