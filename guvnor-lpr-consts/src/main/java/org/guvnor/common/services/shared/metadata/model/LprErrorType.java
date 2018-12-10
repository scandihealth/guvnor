package org.guvnor.common.services.shared.metadata.model;

import java.util.HashMap;
import java.util.Map;

public enum LprErrorType {
    ERROR( "Fejl", "2" ),
    WARN( "Advarsel", "1" );

    private String id;
    private static final Map<String, LprErrorType> _idMap = new HashMap<String, LprErrorType>();
    private final String displayText;

    //initialize id map
    static {
        for ( LprErrorType errorType : LprErrorType.values() ) {
            _idMap.put( errorType.id, errorType );
        }
    }

    LprErrorType( String displayText, String id ) {
        this.displayText = displayText;
        this.id = id;
    }

    /**
     * The Id is used to make the enum searchable using LuceneSearchIndex with a WildcardQuery (the query works only with numbers as strings).
     * When an enum is saved, its id number is saved in the guvnor 'dot' xml file as a string, instead of the enum value itself.
     */
    public String getId() {
        return id;
    }

    public String getDisplayText() {
        return displayText;
    }

    /**
     * @see #getId()
     */
    public static LprErrorType getById( String id ) {
        LprErrorType errorType = _idMap.get( id );
        return errorType != null ? errorType : LprErrorType.values()[0];
    }
}
