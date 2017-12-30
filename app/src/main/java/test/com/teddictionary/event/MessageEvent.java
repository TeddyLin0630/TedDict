package test.com.teddictionary.event;

import java.util.ArrayList;

import test.com.teddictionary.Model.Definition;

/**
 * Created by teddylin on 30/12/2017.
 */

public class MessageEvent {
    public static class SaveDefinition2Quizlet {
        private Definition definition;

        public SaveDefinition2Quizlet(Definition definition) {
            this.definition = definition;
        }

        public Definition getDefinition() {
            return this.definition;
        }
    }

    public static class GetDefitionResult {
        private ArrayList<Definition> definitions = new ArrayList<>();

        public GetDefitionResult(ArrayList<Definition> definitions) {
            this.definitions = definitions;
        }

        public ArrayList<Definition> getDefinitions() {
            return this.definitions;
        }
    }
}
