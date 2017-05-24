package org.guvnor.common.services.backend.metadata.attribute;

import java.util.Map;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.commons.data.Pair;
import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.base.AbstractBasicFileAttributeView;
import org.uberfire.java.nio.base.AbstractPath;
import org.uberfire.java.nio.base.NeedsPreloadedAttrs;
import org.uberfire.java.nio.base.NotImplementedException;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;

import static org.uberfire.commons.data.Pair.*;
import static org.uberfire.commons.validation.PortablePreconditions.*;

/**
 * Created by prc on 16-02-2017.
 */
public class LprMetaView
        extends AbstractBasicFileAttributeView<AbstractPath>
        implements NeedsPreloadedAttrs
         {

    public static final String LPRMETA = "lprmeta";
    public static final String TYPE = LPRMETA + ".type";
    public static final String RULE_VALID_FROM_DATE = LPRMETA + ".ruleValidFromDate";
    public static final String RULE_VALID_TO_DATE = LPRMETA + ".ruleValidToDate";
    public static final String IS_DRAFT = LPRMETA + ".isdraft";
    public static final String IN_PRODUCTION = LPRMETA + ".inproduction";
    public static final String ERROR_NUMBER = LPRMETA + ".errorNumber";
    public static final String ERROR_TEXT = LPRMETA + ".errorText";
    public static final String RULE_GROUP = LPRMETA + ".ruleGroup";
    public static final String ERROR_TYPE = LPRMETA + ".errorType";

    private final LprMetaAttributes attrs;

    public LprMetaView( final AbstractPath path ) {
        super( path );

        final Map<String, Object> content = path.getAttrStorage().getContent();

        final BasicFileAttributes fileAttrs = path.getFileSystem().provider().getFileAttributeView( path, BasicFileAttributeView.class ).readAttributes();

        final LprMetaAttributesImpl lprMetaAttributes = new LprMetaAttributesImpl(fileAttrs);

        for ( final Map.Entry<String, Object> entry : content.entrySet() ) {
            if ( entry.getKey().startsWith( TYPE ) ) {
                lprMetaAttributes.setType( LprRuleType.RuleType.valueOf( entry.getValue().toString() ) );
            }
            if ( entry.getKey().startsWith( RULE_VALID_FROM_DATE ) ) {
                Long timestamp = Long.valueOf( String.valueOf( entry.getValue() ) );
                lprMetaAttributes.setRuleValidFromDate( timestamp );

            }
            if ( entry.getKey().startsWith( RULE_VALID_TO_DATE ) ) {
                lprMetaAttributes.setRuleValidToDate( Long.valueOf( String.valueOf( entry.getValue() ) ) );
            }
            if ( entry.getKey().startsWith( IS_DRAFT ) ) {
                lprMetaAttributes.setDraft((Boolean) entry.getValue());
            }
            if ( entry.getKey().startsWith( IN_PRODUCTION ) ) {
                lprMetaAttributes.setInProduction((Boolean)entry.getValue());
            }
            if ( entry.getKey().startsWith( ERROR_NUMBER ) ) {
                final Object value = entry.getValue();
                final String sValue = value.toString();
                final Long lValue = Long.parseLong(sValue);
                lprMetaAttributes.setErrorNumber(lValue);
            }
            if ( entry.getKey().startsWith( ERROR_TEXT ) ) {
                lprMetaAttributes.setErrorText((String) entry.getValue());
            }
            if ( entry.getKey().startsWith( RULE_GROUP ) ) {
                lprMetaAttributes.setRuleGroup((String) entry.getValue());
            }
            if ( entry.getKey().startsWith( ERROR_TYPE ) ) {
                lprMetaAttributes.setErrorType( LprErrorType.valueOf( entry.getValue().toString() ) );
            }
        }
        //todo ttn this is very confusing.. remove this anonymous class and instead use LprMetaAttributesImpl
        this.attrs = new LprMetaAttributes() {
            @Override
            public LprRuleType.RuleType Type() { return lprMetaAttributes.Type(); }

            @Override
            public Long ruleValidFromDate() { return lprMetaAttributes.ruleValidFromDate(); }

            @Override
            public Long ruleValidToDate() { return lprMetaAttributes.ruleValidToDate(); }

            @Override
            public boolean isDraft() { return lprMetaAttributes.isDraft(); }

            @Override
            public boolean inProduction() { return lprMetaAttributes.inProduction(); }

            @Override
            public Long errorNumber() { return lprMetaAttributes.errorNumber(); }

            @Override
            public String errorText() { return lprMetaAttributes.errorText(); }

            @Override
            public String ruleGroup() { return lprMetaAttributes.ruleGroup(); }

            @Override
            public LprErrorType errorType() { return lprMetaAttributes.errorType(); }

            @Override
            public FileTime lastModifiedTime() {
                return fileAttrs.lastModifiedTime();
            }

            @Override
            public FileTime lastAccessTime() {
                return fileAttrs.lastAccessTime();
            }

            @Override
            public FileTime creationTime() {
                return fileAttrs.creationTime();
            }

            @Override
            public boolean isRegularFile() {
                return fileAttrs.isRegularFile();
            }

            @Override
            public boolean isDirectory() {
                return fileAttrs.isDirectory();
            }

            @Override
            public boolean isSymbolicLink() {
                return fileAttrs.isSymbolicLink();
            }

            @Override
            public boolean isOther() {
                return fileAttrs.isOther();
            }

            @Override
            public long size() {
                return fileAttrs.size();
            }

            @Override
            public Object fileKey() {
                return fileAttrs.fileKey();
            }
        };
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
                 return new Class[]{ LprMetaView.class };
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
