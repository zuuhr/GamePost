package es.codeurjc.gamepost.objects;

import java.util.Dictionary;

public class Forum {
    Dictionary<Integer, ForumEntry> forumEntries;    

    public void sortBy(ForumColumnsEnum column, boolean isAscendent){
        //TODO: pues eso. Hacer el método sortBy.
    }
}

enum ForumColumnsEnum{
    Title,
    CreationDate,
    LastUpdateDate,
    NumResponses,
    Votes
}
