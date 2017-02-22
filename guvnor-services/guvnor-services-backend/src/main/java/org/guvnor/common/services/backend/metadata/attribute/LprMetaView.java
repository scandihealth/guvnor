package org.guvnor.common.services.backend.metadata.attribute;

import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.commons.data.Pair;
import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.base.AbstractBasicFileAttributeView;
import org.uberfire.java.nio.base.AbstractPath;
import org.uberfire.java.nio.base.NeedsPreloadedAttrs;
import org.uberfire.java.nio.base.NotImplementedException;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;

import java.util.Date;
import java.util.Map;

import static org.uberfire.commons.data.Pair.newPair;
import static org.uberfire.commons.validation.PortablePreconditions.checkCondition;
import static org.uberfire.commons.validation.PortablePreconditions.checkNotEmpty;

/**
 * Created by prc on 16-02-2017.
 */
public class LprMetaView
        extends AbstractBasicFileAttributeView<AbstractPath>
        implements NeedsPreloadedAttrs
         {

    public static final String LPRMETA = "lprmeta";
    public static final String TYPE = LPRMETA + ".type";
    public static final String VALID_FROM = LPRMETA + ".validfrom";
    public static final String VALID_TO = LPRMETA + ".validto";
    public static final String IS_DRAFT = LPRMETA + ".isdraft";
    public static final String IN_PRODUCTION = LPRMETA + ".inproduction";

    private final LprMetaAttributes attrs;

    public LprMetaView( final AbstractPath path ) {
        super( path );

        final Map<String, Object> content = path.getAttrStorage().getContent();

        final BasicFileAttributes fileAttrs = path.getFileSystem().provider().getFileAttributeView( path, BasicFileAttributeView.class ).readAttributes();

        final LprMetaAttributesImpl lprMetaAttributes = new LprMetaAttributesImpl(fileAttrs);

        for ( final Map.Entry<String, Object> entry : content.entrySet() ) {
            if ( entry.getKey().startsWith( TYPE ) ) {
                lprMetaAttributes.setType((LprRuleType.RuleType)entry.getValue());
            }
            if ( entry.getKey().startsWith( VALID_FROM ) ) {
                try {
                    final Object value = entry.getValue();
                    //Long lValue = (Long) value;
                    final String sValue = value.toString();
                    final Long lValue = Long.parseLong(sValue, 10);
                    lprMetaAttributes.setValidFrom(lValue);
                }
                catch (Exception e)
                {
                    lprMetaAttributes.setValidFrom(new Date().getTime());
                }

            }
            if ( entry.getKey().startsWith( VALID_TO ) ) {
                try {
                    final Object value = entry.getValue();
                    final String sValue = value.toString();
                    final Long lValue = Long.parseLong(sValue, 10);
                    lprMetaAttributes.setValidTo(lValue);
                }
                catch(Exception e)
                {
                    lprMetaAttributes.setValidTo(new Date().getTime());
                }
            }
            if ( entry.getKey().startsWith( IS_DRAFT ) ) {
                lprMetaAttributes.setDraft((Boolean) entry.getValue());
            }
            if ( entry.getKey().startsWith( IN_PRODUCTION ) ) {
                lprMetaAttributes.setInProduction((Boolean)entry.getValue());
            }
        }
        this.attrs = lprMetaAttributes;
    }

             @Override
             public String name() {
                 return LPRMETA;
             }

             @Override
             public LprMetaAttributes readAttributes() throws IOException {
                 return attrs;
             }

             @Override
             public Map<String, Object> readAttributes( final String... attributes ) {
                 return LprMetaAttributesUtil.toMap( readAttributes(), attributes );
             }

             @Override
             public Class<? extends BasicFileAttributeView>[] viewTypes() {
                 return new Class[]{ DiscussionView.class };
             }

             @Override
             public void setAttribute( final String attribute,
                                       final Object value ) throws IOException {
                 checkNotEmpty( "attribute", attribute );
                 checkCondition( "invalid attribute", attribute.equals( name() ) );

                 throw new NotImplementedException();
             }

    private Pair<Integer, String> extractValue(final Map.Entry<String, Object> entry ) {
        int start = entry.getKey().indexOf( '[' );
        if ( start < 0 ) {
            return newPair( 0, entry.getValue().toString() );
        }
        int end = entry.getKey().indexOf( ']' );

        return newPair( Integer.valueOf( entry.getKey().substring( start + 1, end ) ), entry.getValue().toString() );
    }
}
