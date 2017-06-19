/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.guvnor.common.services.backend.metadata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.guvnor.common.services.backend.metadata.attribute.DiscussionView;
import org.guvnor.common.services.backend.metadata.attribute.LprMetaAttributes;
import org.guvnor.common.services.backend.metadata.attribute.LprMetaView;
import org.guvnor.common.services.backend.metadata.attribute.OtherMetaView;
import org.guvnor.common.services.shared.metadata.model.DiscussionRecord;
import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.backend.vfs.impl.LockInfo;
import org.uberfire.ext.editor.commons.version.impl.PortableVersionRecord;
import org.uberfire.io.IOService;
import org.uberfire.io.attribute.DublinCoreView;
import org.uberfire.java.nio.base.version.VersionAttributeView;
import org.uberfire.java.nio.base.version.VersionAttributes;
import org.uberfire.java.nio.base.version.VersionHistory;
import org.uberfire.java.nio.base.version.VersionRecord;
import org.uberfire.java.nio.file.NoSuchFileException;
import org.uberfire.java.nio.file.Path;
import org.uberfire.rpc.SessionInfo;

import static org.uberfire.commons.validation.PortablePreconditions.*;

public class MetadataCreator {

    private final Path path;
    private final DublinCoreView dublinCoreView;
    private final DiscussionView discussView;
    private final OtherMetaView otherMetaView;
    private final VersionAttributeView versionAttributeView;
    private final LprMetaView lprMetaView;
    private final IOService configIOService;
    private final SessionInfo sessionInfo;

    public MetadataCreator( Path path,
                            IOService configIOService,
                            SessionInfo sessionInfo,
                            DublinCoreView dublinCoreView,
                            DiscussionView discussionView,
                            OtherMetaView otherMetaView,
                            VersionAttributeView versionAttributeView,
                            LprMetaView lprMetaView ) {
        this.path = checkNotNull( "path", path );
        this.configIOService = checkNotNull( "configIOService", configIOService );
        this.sessionInfo = checkNotNull( "sessionInfo", sessionInfo );
        this.dublinCoreView = checkNotNull( "dublinCoreView", dublinCoreView );
        this.discussView = checkNotNull( "discussionView", discussionView );
        this.otherMetaView = checkNotNull( "otherMetaView", otherMetaView );
        this.versionAttributeView = checkNotNull( "versionAttributeView", versionAttributeView );
        this.lprMetaView = checkNotNull( "lprMetaView", lprMetaView );
    }

    public Metadata create() {
        return MetadataBuilder.newMetadata()
                .withPath( Paths.convert( path ) )
                .withRealPath( Paths.convert( path.toRealPath() ) )
                .withCheckinComment( getCheckinComment() )
                .withLastContributor( getLastContributor() )
                .withCreator( getCreator() )
                .withLastModified( getLastModified() )
                .withDateCreated( getDateCreated() )
                .withSubject( getSubject() )
                .withType( getType() )
                .withExternalRelation( getExternalRelation() )
                .withExternalSource( getExternalSource() )
                .withDescription( getDescription() )
                .withTags( getTags() )
                .withDiscussion( getDiscussion() )
                .withLockInfo( retrieveLockInfo( Paths.convert( path ) ) )
                .withVersion( getVersion() )
                .withLprRuleType( getLprRuleType() )
                .withInProduction( getInProduction() )
                .withIsDraft( getIsDraft() )
                .withIsValidForLPRReports( getIsValidForLPRReports() )
                .withIsValidForDUSASAbroadReports( getIsValidForDUSASAbroadReports() )
                .withIsValidForDUSASSpecialityReports( getIsValidForDUSASSpecialityReports() )
                .withReportReceivedFromDate( getReportReceivedFromDate() )
                .withReportReceivedToDate( getReportReceivedToDate() )
                .withEncounterStartFromDate( getEncounterStartFromDate() )
                .withEncounterStartToDate( getEncounterStartToDate() )
                .withEncounterEndFromDate( getEncounterEndFromDate() )
                .withEncounterEndToDate( getEncounterEndToDate() )
                .withEpisodeOfCareStartFromDate( getEpisodeOfCareStartFromDate() )
                .withEpisodeOfCareStartToDate( getEpisodeOfCareStartToDate() )
                .withErrorNumber( getErrorNumber() )
                .withErrorText( getErrorText() )
                .withRuleGroup( getRuleGroup() )
                .withErrorType( getErrorType() )
                .build();
    }

    private ArrayList<VersionRecord> getVersion() {
        return new ArrayList<VersionRecord>( versionAttributeView.readAttributes().history().records().size() ) {{
            for ( final VersionRecord record : versionAttributeView.readAttributes().history().records() ) {
                add( new PortableVersionRecord( record.id(), record.author(), record.email(), record.comment(), record.date(), record.uri() ) );
            }
        }};
    }

    private List<DiscussionRecord> getDiscussion() {
        return discussView.readAttributes().discussion();
    }

    private List<String> getTags() {
        return otherMetaView.readAttributes().tags();
    }

    private String getDescription() {
        return dublinCoreView.readAttributes().descriptions().size() > 0 ? dublinCoreView.readAttributes().descriptions().get( 0 ) : null;
    }

    private String getExternalSource() {
        return dublinCoreView.readAttributes().sources().size() > 0 ? dublinCoreView.readAttributes().sources().get( 0 ) : null;
    }

    private String getExternalRelation() {
        return dublinCoreView.readAttributes().relations().size() > 0 ? dublinCoreView.readAttributes().relations().get( 0 ) : null;
    }

    private String getType() {
        return dublinCoreView.readAttributes().types().size() > 0 ? dublinCoreView.readAttributes().types().get( 0 ) : null;
    }

    private String getSubject() {
        return dublinCoreView.readAttributes().subjects().size() > 0 ? dublinCoreView.readAttributes().subjects().get( 0 ) : null;
    }

    private Date getDateCreated() {
        return new Date( versionAttributeView.readAttributes().creationTime().toMillis() );
    }

    private Date getLastModified() {
        return new Date( versionAttributeView.readAttributes().lastModifiedTime().toMillis() );
    }

    private String getCreator() {
        if ( versionAttributeView.readAttributes().history().records().size() > 0 ) {
            return versionAttributeView.readAttributes().history().records().get( 0 ).author();
        } else {
            return null;
        }
    }

    private String getLastContributor() {
        if ( versionAttributeView.readAttributes().history().records().size() > 0 ) {
            return versionAttributeView.readAttributes().history().records().get( versionAttributeView.readAttributes().history().records().size() - 1 ).author();
        } else {
            return null;
        }
    }

    private String getCheckinComment() {
        VersionAttributes versionAttributes = versionAttributeView.readAttributes();
        VersionHistory history = versionAttributes.history();
        List<VersionRecord> records = history.records();
        if ( records.size() > 0 ) {
            return versionAttributeView.readAttributes().history().records().get( versionAttributeView.readAttributes().history().records().size() - 1 ).comment();
        } else {
            return null;
        }
    }

    private LockInfo retrieveLockInfo( org.uberfire.backend.vfs.Path path ) {
        final org.uberfire.java.nio.file.Path lockPath = Paths.convert( PathFactory.newLock( path ) );
        try {
            //See https://issues.jboss.org/browse/GUVNOR-2399. We simply try to read the lock file returning a default.
            final String lockedBy = configIOService.readAllString( lockPath );
            return new LockInfo( true,
                    lockedBy,
                    path );

        } catch ( NoSuchFileException nsfe ) {
            return new LockInfo( false,
                    "",
                    path );
        }
    }

    private LprRuleType getLprRuleType() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.ruleType();
    }

    private boolean getInProduction() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.inProduction();
    }

    private boolean getIsDraft() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.isDraft();
    }
    private boolean getIsValidForLPRReports() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.isValidForLPRReports();
    }
    private boolean getIsValidForDUSASAbroadReports() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.isValidForDUSASAbroadReports();
    }
    private boolean getIsValidForDUSASSpecialityReports() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.isValidForDUSASSpecialityReports();
    }

    private Long getReportReceivedFromDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.reportReceivedFromDate();
    }

    private Long getReportReceivedToDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.reportReceivedToDate();
    }

    private Long getEncounterStartFromDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.encounterStartFromDate();
    }

    private Long getEncounterStartToDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.encounterStartToDate();
    }

    private Long getEncounterEndFromDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.encounterEndFromDate();
    }

    private Long getEncounterEndToDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.encounterEndToDate();
    }

    private Long getEpisodeOfCareStartFromDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.episodeOfCareStartFromDate();
    }

    private Long getEpisodeOfCareStartToDate() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.episodeOfCareStartToDate();
    }

    private Long getErrorNumber() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.errorNumber();
    }

    private String getErrorText() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.errorText();
    }

    private String getRuleGroup() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.ruleGroup();
    }

    private LprErrorType getErrorType() {
        LprMetaAttributes lprMetaAttributes = lprMetaView.readAttributes();
        return lprMetaAttributes.errorType();
    }
}
