package org.guvnor.common.services.backend.metadata.attribute;

import java.util.Map;

import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.base.AbstractBasicFileAttributeView;
import org.uberfire.java.nio.base.AbstractPath;
import org.uberfire.java.nio.base.NeedsPreloadedAttrs;
import org.uberfire.java.nio.base.NotImplementedException;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;

import static org.guvnor.common.services.shared.metadata.model.LprMetadataConsts.*;
import static org.uberfire.commons.validation.PortablePreconditions.*;

/**
 * Created on 16-02-2017.
 */
public class LprMetaView
        extends AbstractBasicFileAttributeView<AbstractPath>
        implements NeedsPreloadedAttrs {

    private final LprMetaAttributes attrs;

    public LprMetaView( final AbstractPath path ) {
        super( path );

        final Map<String, Object> content = path.getAttrStorage().getContent();

        final BasicFileAttributes fileAttrs = path.getFileSystem().provider().getFileAttributeView( path, BasicFileAttributeView.class ).readAttributes();

        this.attrs = new LprMetaAttributes() {
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

            @Override
            public LprRuleType ruleType() {
                return content.containsKey( RULE_TYPE ) ? LprRuleType.valueOf( ( String ) content.get( RULE_TYPE ) ) : LprRuleType.REPORT_VALIDATION;
            }

            @Override
            public Long reportReceivedFromDate() {
                return content.containsKey( REPORT_RECEIVED_FROM_DATE ) ? ( Long ) content.get( REPORT_RECEIVED_FROM_DATE ) : 0L;
            }

            @Override
            public Long reportReceivedToDate() {
                return content.containsKey( REPORT_RECEIVED_TO_DATE ) ? ( Long ) content.get( REPORT_RECEIVED_TO_DATE ) : Long.MAX_VALUE;
            }

            @Override
            public Long encounterStartFromDate() {
                return content.containsKey( ENCOUNTER_START_FROM_DATE ) ? ( Long ) content.get( ENCOUNTER_START_FROM_DATE ) : 0L;
            }

            @Override
            public Long encounterStartToDate() {
                return content.containsKey( ENCOUNTER_START_TO_DATE ) ? ( Long ) content.get( ENCOUNTER_START_TO_DATE ) : Long.MAX_VALUE;
            }

            @Override
            public Long encounterEndFromDate() {
                return content.containsKey( ENCOUNTER_END_FROM_DATE ) ? ( Long ) content.get( ENCOUNTER_END_FROM_DATE ) : 0L;
            }

            @Override
            public Long encounterEndToDate() {
                return content.containsKey( ENCOUNTER_END_TO_DATE ) ? ( Long ) content.get( ENCOUNTER_END_TO_DATE ) : Long.MAX_VALUE;
            }

            @Override
            public Long episodeOfCareStartFromDate() {
                return content.containsKey( EPISODE_OF_CARE_START_FROM_DATE ) ? ( Long ) content.get( EPISODE_OF_CARE_START_FROM_DATE ) : 0L;
            }

            @Override
            public Long episodeOfCareStartToDate() {
                return content.containsKey( EPISODE_OF_CARE_START_TO_DATE ) ? ( Long ) content.get( EPISODE_OF_CARE_START_TO_DATE ) : Long.MAX_VALUE;
            }

            @Override
            public boolean isDraft() {
                return content.containsKey( IS_DRAFT ) ? ( Boolean ) content.get( IS_DRAFT ) : Boolean.FALSE;
            }

            @Override
            public boolean inProduction() {
                return content.containsKey( IN_PRODUCTION ) ? ( Boolean ) content.get( IN_PRODUCTION ) : Boolean.FALSE;
            }

            @Override
            public boolean isValidForLPRReports() {
                return content.containsKey( IS_VALID_FOR_LPR_REPORTS ) ? ( Boolean ) content.get( IS_VALID_FOR_LPR_REPORTS ) : Boolean.FALSE;
            }

            @Override
            public boolean isValidForDUSASAbroadReports() {
                return content.containsKey( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ) ? ( Boolean ) content.get( IS_VALID_FOR_DUSAS_ABROAD_REPORTS ) : Boolean.FALSE;
            }

            @Override
            public boolean isValidForDUSASSpecialityReports() {
                return content.containsKey( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ) ? ( Boolean ) content.get( IS_VALID_FOR_DUSAS_SPECIALITY_REPORTS ) : Boolean.FALSE;
            }

            @Override
            public Long errorNumber() {
                //errorNumber is stored as string to make wild card searches possible
                return Long.valueOf( content.containsKey( ERROR_NUMBER ) ? content.get( ERROR_NUMBER ).toString() : "0" );
            }

            @Override
            public String errorText() {
                return content.containsKey( ERROR_TEXT ) ? ( String ) content.get( ERROR_TEXT ) : "";
            }

            @Override
            public String ruleGroup() {
                return content.containsKey( RULE_GROUP ) ? ( String ) content.get( RULE_GROUP ) : "";
            }

            @Override
            public LprErrorType errorType() {
                return content.containsKey( ERROR_TYPE ) ? LprErrorType.valueOf( ( String ) content.get( ERROR_TYPE ) ) : LprErrorType.NONE;
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
        return new Class[]{LprMetaView.class};
    }

    @Override
    public void setAttribute( final String attribute,
                              final Object value ) throws IOException {
        checkNotEmpty( "attribute", attribute );
        checkCondition( "invalid attribute", attribute.equals( name() ) );

        throw new NotImplementedException();
    }
}
