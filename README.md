# Poker Hand Sorter

### Running the application
Within the project folder, please run the following:
```
mvn clean package && cat poker-hands.txt | java -jar target/poker-challenge-0.0.1.jar
```

### Potential improvements
`#IMPROVEMENT-1` - I could've enhanced the hands validation by using a RegExp to match the two player's hands

`#IMPROVEMENT-2` - I'm not sure whether I should've designed a `TwoPairTieBreaker` given the `PairTieBreaker` may not be enough to cover all two pair scenarios