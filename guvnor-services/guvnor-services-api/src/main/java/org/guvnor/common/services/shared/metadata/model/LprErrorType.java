package org.guvnor.common.services.shared.metadata.model;

//todo ttn consider if this enum should be declared in LPR3 and imported as maven dependency in drools-wb
public enum LprErrorType {
    NONE( "" ),
    WARNING( "Advarsel" ),
    ERROR( "Fejl" ),
    FATAL( "Fatal" );

    private final String displayText;

    LprErrorType( String displayText ) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
