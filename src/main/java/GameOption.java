package main.java;

public enum GameOption {

    ROCK() {
        @Override
        public boolean beats(GameOption gameOption)
        {
            return gameOption == LIZARD || gameOption == SCISSORS;
        }
    },
    PAPER(){
        @Override
        public boolean beats(GameOption gameOption)
        {
            return gameOption == SPOCK || gameOption == ROCK;
        }
    },
    SCISSORS{
        @Override
        public boolean beats(GameOption gameOption)
        {
            return gameOption == PAPER || gameOption == LIZARD;
        }
    },
    LIZARD {
        @Override
        public boolean beats(GameOption gameOption)
        {
            return gameOption == PAPER || gameOption == SPOCK;
        }
    },
    SPOCK {
        @Override
        public boolean beats(GameOption gameOption)
        {
            return gameOption == ROCK || gameOption == SCISSORS;
        }
    }
    ;

    public abstract boolean beats(GameOption gameOption);
}
