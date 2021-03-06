/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.guvnor.structure.client.editors.repository.list;

import java.util.ArrayList;
import java.util.Collections;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.guvnor.structure.client.editors.context.GuvnorStructureContext;
import org.guvnor.structure.repositories.PublicURI;
import org.guvnor.structure.repositories.Repository;
import org.uberfire.ext.widgets.core.client.resources.i18n.CoreConstants;

@Dependent
public class RepositoryItemPresenter
        implements IsWidget {

    private Repository repository;

    private RepositoryItemView     view;
    private GuvnorStructureContext guvnorStructureContext;
    private HasRemoveRepositoryHandlers removeRepositoryHandler;

    public RepositoryItemPresenter() {
    }

    @Inject
    public RepositoryItemPresenter( final RepositoryItemView repositoryItemView,
                                    final GuvnorStructureContext guvnorStructureContext ) {
        this.view = repositoryItemView;
        this.guvnorStructureContext = guvnorStructureContext;
    }

    public void setRepository( final Repository repository,
                               final String branch ) {
        this.repository = repository;

        view.setPresenter( this );

        view.setRepositoryName( repository.getAlias() );

        view.setRepositoryDescription( CoreConstants.INSTANCE.Empty() );

        if ( repository.getPublicURIs().size() > 0 ) {
            view.showAvailableProtocols();
        }

        setPublicURIs();

        view.setUriId( "view-uri-for-" + repository.getAlias() );

        populateBranches( branch );

        view.refresh();
    }

    public void refreshBranches() {
        populateBranches( view.getSelectedBranch() );
        view.refresh();
    }

    private void setPublicURIs() {
        int count = 0;
        for ( final PublicURI publicURI : repository.getPublicURIs() ) {
            if ( count == 0 ) {
                view.setDaemonURI( publicURI.getURI() );
            }
            final String protocol = getProtocol( publicURI );

            view.addProtocol( protocol );
            count++;
        }
    }

    private void populateBranches( final String currentBranch ) {
        final ArrayList<String> branches = new ArrayList<String>( repository.getBranches() );

        Collections.reverse( branches );

        view.clearBranches();
        for ( String branch : branches ) {
            view.addBranch( branch );
        }
        view.setSelectedBranch( currentBranch );
    }

    public void onAnchorSelected( final String protocol ) {
        for ( PublicURI publicURI : repository.getPublicURIs() ) {
            if ( protocol.equals( getProtocol( publicURI ) ) ) {
                view.setDaemonURI( publicURI.getURI() );
                break;
            }
        }
    }

    private String getProtocol( final PublicURI publicURI ) {
        return publicURI.getProtocol() == null ? "default" : publicURI.getProtocol();
    }

    public void onClickButtonRemoveRepository() {
        if ( removeRepositoryHandler != null ) {
            removeRepositoryHandler.removeRepository( repository );
        }
    }

    public void onUpdateRepository( final String branch ) {
        guvnorStructureContext.changeBranch( repository.getAlias(),
                                             branch );
    }

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }

    public void addRemoveRepositoryCommand( final HasRemoveRepositoryHandlers removeRepositoryHandlers ) {
        this.removeRepositoryHandler = removeRepositoryHandlers;
    }

}
