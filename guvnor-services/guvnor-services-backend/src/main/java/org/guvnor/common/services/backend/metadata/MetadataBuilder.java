/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
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

import org.guvnor.common.services.shared.metadata.model.DiscussionRecord;
import org.guvnor.common.services.shared.metadata.model.LprErrorType;
import org.guvnor.common.services.shared.metadata.model.LprRuleGroup;
import org.guvnor.common.services.shared.metadata.model.LprRuleType;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.impl.LockInfo;
import org.uberfire.java.nio.base.version.VersionRecord;

import static org.uberfire.commons.validation.PortablePreconditions.*;

/**
 *
 */
public final class MetadataBuilder {

    private Path path;
    private Path realPath;
    private LockInfo lockInfo;

    //git info
    private String checkinComment;
    private String lastContributor;
    private String creator;

    private Date lastModified;
    private Date dateCreated;

    //pure dcore
    private String subject;
    private String type;
    private String externalRelation;
    private String externalSource;
    private String description;

    //not dcore
    private List<String> tags = new ArrayList<String>();
    private List<DiscussionRecord> discussion = new ArrayList<DiscussionRecord>();
    private List<VersionRecord> version = new ArrayList<VersionRecord>();

    // lpr
    private LprRuleType lprRuleType;
    private Long reportReceivedFromDate;
    private Long reportReceivedToDate;
    private Long encounterStartFromDate;
    private Long encounterStartToDate;
    private Long encounterEndFromDate;
    private Long encounterEndToDate;
    private Long episodeOfCareStartFromDate;
    private Long episodeOfCareStartToDate;
    private boolean isDraft;
    private boolean inProduction;
    private boolean isValidForLPRReports;
    private boolean isValidForDUSASAbroadReports;
    private boolean isValidForDUSASSpecialityReports;
    private Long errorNumber;
    private String errorText;
    private LprRuleGroup ruleGroup;
    private LprErrorType errorType;

    private MetadataBuilder() {

    }

    public static MetadataBuilder newMetadata() {
        return new MetadataBuilder();
    }

    public MetadataBuilder withPath( final Path path ) {
        this.path = checkNotNull( "path", path );
        return this;
    }

    public MetadataBuilder withRealPath( final Path realPath ) {
        this.realPath = checkNotNull( "realPath", realPath );
        return this;
    }

    public MetadataBuilder withCheckinComment( final String checkinComment ) {
        this.checkinComment = checkinComment;
        return this;
    }

    public MetadataBuilder withLastContributor( final String lastContributor ) {
        this.lastContributor = lastContributor;
        return this;
    }

    public MetadataBuilder withCreator( final String creator ) {
        this.creator = checkNotEmpty( "creator", creator );
        return this;
    }

    public MetadataBuilder withLastModified( final Date date ) {
        this.lastModified = checkNotNull( "date", date );
        return this;
    }

    public MetadataBuilder withDateCreated( final Date date ) {
        this.dateCreated = checkNotNull( "date", date );
        return this;
    }

    public MetadataBuilder withSubject( final String subject ) {
        this.subject = subject;
        return this;
    }

    public MetadataBuilder withType( final String type ) {
        this.type = type;
        return this;
    }

    public MetadataBuilder withExternalRelation( final String externalRelation ) {
        this.externalRelation = externalRelation;
        return this;
    }

    public MetadataBuilder withExternalSource( final String externalSource ) {
        this.externalSource = externalSource;
        return this;
    }

    public MetadataBuilder withDescription( final String description ) {
        this.description = description;
        return this;
    }

    public MetadataBuilder withTags( final List<String> tags ) {
        this.tags = tags;
        return this;
    }

    public MetadataBuilder withDiscussion( final List<DiscussionRecord> discussion ) {
        this.discussion = discussion;
        return this;
    }

    public MetadataBuilder withVersion( final List<VersionRecord> version ) {
        this.version = version;
        return this;
    }

    public MetadataBuilder withLockInfo( final LockInfo lockInfo ) {
        this.lockInfo = lockInfo;
        return this;
    }

    public MetadataBuilder withLprRuleType( final LprRuleType lprRuleType ) {
        this.lprRuleType = lprRuleType;
        return this;
    }

    public MetadataBuilder withReportReceivedFromDate( final Long reportReceivedFromDate ) {
        this.reportReceivedFromDate = reportReceivedFromDate;
        return this;
    }

    public MetadataBuilder withReportReceivedToDate( final Long reportReceivedToDate ) {
        this.reportReceivedToDate = reportReceivedToDate;
        return this;
    }

    public MetadataBuilder withEncounterStartFromDate( final Long encounterStartFromDate ) {
        this.encounterStartFromDate = encounterStartFromDate;
        return this;
    }

    public MetadataBuilder withEncounterStartToDate( final Long encounterStartToDate ) {
        this.encounterStartToDate = encounterStartToDate;
        return this;
    }

    public MetadataBuilder withEncounterEndFromDate( final Long encounterEndFromDate) {
        this.encounterEndFromDate = encounterEndFromDate;
        return this;
    }

    public MetadataBuilder withEncounterEndToDate( final Long encounterEndToDate) {
        this.encounterEndToDate = encounterEndToDate;
        return this;
    }

    public MetadataBuilder withEpisodeOfCareStartFromDate( final Long episodeOfCareStartFromDate) {
        this.episodeOfCareStartFromDate = episodeOfCareStartFromDate;
        return this;
    }

    public MetadataBuilder withEpisodeOfCareStartToDate( final Long episodeOfCareStartToDate) {
        this.episodeOfCareStartToDate = episodeOfCareStartToDate;
        return this;
    }

    public MetadataBuilder withIsDraft( final boolean isDraft ) {
        this.isDraft = isDraft;
        return this;
    }

    public MetadataBuilder withInProduction( final boolean inProduction ) {
        this.inProduction = inProduction;
        return this;
    }
    public MetadataBuilder withIsValidForLPRReports( final boolean isValidForLPRReports ) {
        this.isValidForLPRReports = isValidForLPRReports;
        return this;
    }
    public MetadataBuilder withIsValidForDUSASAbroadReports( final boolean isValidForDUSASAbroadReports ) {
        this.isValidForDUSASAbroadReports = isValidForDUSASAbroadReports;
        return this;
    }
    public MetadataBuilder withIsValidForDUSASSpecialityReports( final boolean isValidForDUSASSpecialityReports ) {
        this.isValidForDUSASSpecialityReports = isValidForDUSASSpecialityReports;
        return this;
    }

    public MetadataBuilder withErrorNumber( final Long errorNumber ) {
        this.errorNumber = errorNumber;
        return this;
    }

    public MetadataBuilder withErrorText( final String errorText ) {
        this.errorText = errorText;
        return this;
    }

    public MetadataBuilder withRuleGroup( final LprRuleGroup ruleGroup ) {
        this.ruleGroup = ruleGroup;
        return this;
    }

    public MetadataBuilder withErrorType( final LprErrorType errorType ) {
        this.errorType = errorType;
        return this;
    }

    public Metadata build() {
        return new Metadata( path,
                realPath,
                checkinComment,
                lastContributor,
                creator,
                lastModified,
                dateCreated,
                subject,
                type,
                externalRelation,
                externalSource,
                description,
                tags,
                discussion,
                version,
                lockInfo,
                lprRuleType,
                reportReceivedFromDate,
                reportReceivedToDate,
                encounterStartFromDate,
                encounterStartToDate,
                encounterEndFromDate,
                encounterEndToDate,
                episodeOfCareStartFromDate,
                episodeOfCareStartToDate,
                isDraft,
                inProduction,
                isValidForLPRReports,
                isValidForDUSASAbroadReports,
                isValidForDUSASSpecialityReports,
                errorNumber,
                errorText,
                ruleGroup,
                errorType );
    }
}
