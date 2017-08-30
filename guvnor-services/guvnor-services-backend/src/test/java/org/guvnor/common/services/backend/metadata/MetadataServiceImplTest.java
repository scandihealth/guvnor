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
import org.guvnor.common.services.backend.metadata.attribute.LprMetaView;
import org.guvnor.common.services.backend.metadata.attribute.OtherMetaView;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.io.IOService;
import org.uberfire.io.attribute.DublinCoreView;
import org.uberfire.java.nio.base.version.VersionAttributeView;
import org.uberfire.java.nio.base.version.VersionAttributes;
import org.uberfire.java.nio.base.version.VersionRecord;
import org.uberfire.java.nio.file.Path;
import org.uberfire.java.nio.fs.file.SimpleFileSystemProvider;
import org.uberfire.rpc.SessionInfo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceImplTest {

    private SimpleFileSystemProvider fileSystemProvider;

    @Mock
    private IOService ioService;

    @Mock
    private IOService configIOService;

    @Mock
    private SessionInfo sessionInfo;

    @Mock
    private OtherMetaView otherMetaView;

    @Mock
    private LprMetaView lprMetaView;

    @Mock
    private DublinCoreView dublinCoreView;

    @Mock
    private DiscussionView discussView;

    @Mock
    private VersionAttributeView versionAttributeView;

    private Path path;
    private MetadataServerSideService service;
    private ArrayList<VersionRecord> versionRecords;

    @Before
    public void setUp() throws Exception {
        fileSystemProvider = new SimpleFileSystemProvider();

        //Ensure URLs use the default:// scheme
        fileSystemProvider.forceAsDefault();

        path = fileSystemProvider.getPath( this.getClass().getResource( "myfile.file" ).toURI() );

        service = new MetadataServiceImpl( ioService, configIOService, sessionInfo );

        versionRecords = new ArrayList<VersionRecord>();
        versionRecords.add( createVersionRecord() );

        VersionAttributes versionAttributes = new VersionAttributesMock( versionRecords );
        when( versionAttributeView.readAttributes() ).thenReturn( versionAttributes );
    }

    @Test
    public void testGetEmptyTagsNoOtherMetaView() {
        final List<String> tags = service.getTags( path );

        assertNotNull( tags );
        assertEquals( 0,
                tags.size() );
    }

    @Test
    public void testGetEmptyTags() {
        when( otherMetaView.readAttributes() ).thenReturn( new OtherMetaAttributesMock() );
        when( ioService.getFileAttributeView( path,
                OtherMetaView.class ) ).thenReturn( otherMetaView );
        final List<String> tags = service.getTags( path );

        assertNotNull( tags );
        assertEquals( 0,
                tags.size() );
    }

    @Test
    public void testGetTags() {
        when( otherMetaView.readAttributes() ).thenReturn( new OtherMetaAttributesMock() {

            List<String> tags = new ArrayList<String>() {{
                add( "tag1" );
            }};

            @Override
            public List<String> tags() {
                return tags;
            }
        } );
        when( ioService.getFileAttributeView( path,
                OtherMetaView.class ) ).thenReturn( otherMetaView );
        final List<String> tags = service.getTags( path );

        assertNotNull( tags );
        assertEquals( 1,
                tags.size() );
    }

    @Test
    public void testGetLprMetadata() {
        LprMetaAttributesMock lprMock = new LprMetaAttributesMock();
        when( lprMetaView.readAttributes() ).thenReturn( lprMock );
        when( ioService.getFileAttributeView( path,
                LprMetaView.class ) ).thenReturn( lprMetaView );
        when( otherMetaView.readAttributes() ).thenReturn( new OtherMetaAttributesMock() );
        when( ioService.getFileAttributeView( path,
                OtherMetaView.class ) ).thenReturn( otherMetaView );
        when( dublinCoreView.readAttributes() ).thenReturn( new DublinCoreAttributesMock() );
        when( ioService.getFileAttributeView( path,
                DublinCoreView.class ) ).thenReturn( dublinCoreView );
        when( discussView.readAttributes() ).thenReturn( new DiscussionAttributesMock() );
        when( ioService.getFileAttributeView( path,
                DiscussionView.class ) ).thenReturn( discussView );
        //when( versionAttributeView.readAttributes() ).thenReturn( new VersionAttributesMock() );
        when( ioService.getFileAttributeView( path,
                VersionAttributeView.class ) ).thenReturn( versionAttributeView );

        final Metadata metadata = service.getMetadata( path );

        assertEquals( lprMock.ruleType(), metadata.getRuleType() );
        assertEquals( lprMock.reportReceivedFromDate(), metadata.getReportReceivedFromDate() );
        assertEquals( lprMock.reportReceivedToDate(), metadata.getReportReceivedToDate() );
        assertEquals( lprMock.encounterStartFromDate(), metadata.getEncounterStartFromDate() );
        assertEquals( lprMock.encounterStartToDate(), metadata.getEncounterStartToDate() );
        assertEquals( lprMock.encounterEndFromDate(), metadata.getEncounterEndFromDate() );
        assertEquals( lprMock.encounterEndToDate(), metadata.getEncounterEndToDate() );
        assertEquals( lprMock.episodeOfCareStartFromDate(), metadata.getEpisodeOfCareStartFromDate() );
        assertEquals( lprMock.episodeOfCareStartToDate(), metadata.getEpisodeOfCareStartToDate() );
        assertEquals( lprMock.errorText(), metadata.getErrorText() );
        assertEquals( lprMock.errorType(), metadata.getErrorType() );
        assertEquals( lprMock.ruleGroup(), metadata.getRuleGroup() );
        assertEquals( lprMock.errorNumber(), metadata.getErrorNumber() );
        assertEquals( lprMock.productionDate(), metadata.getProductionDate() );
        assertEquals( lprMock.archivedDate(), metadata.getArchivedDate() );
        assertEquals( lprMock.isValidForLPRReports(), metadata.isValidForLPRReports() );
        assertEquals( lprMock.isValidForDUSASSpecialityReports(), metadata.isValidForDUSASAbroadReports() );
        assertEquals( lprMock.isValidForDUSASSpecialityReports(), metadata.isValidForDUSASSpecialityReports() );
    }

    private VersionRecord createVersionRecord() {
        return new VersionRecord() {
            @Override
            public String id() {
                return "1";
            }

            @Override
            public String author() {
                return "admin";
            }

            @Override
            public String email() {
                return "admin@mail.zap";
            }

            @Override
            public String comment() {
                return "Some commit";
            }

            @Override
            public Date date() {
                return new Date();
            }

            @Override
            public String uri() {
                return "myfile.file";
            }
        };
    }
}