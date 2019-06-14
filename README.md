# Poker Hand Sorter

### Running the application
Within the project folder, please run the following:
```
mvn clean package && cat poker-hands.txt | java -jar target/poker-challenge-0.0.1.jar
```

### Opening test coverage results
Within the project folder, please run: (Note: replace Google Chrome with you desired web browser)
```$xslt
open -a "Google Chrome" target/site/jacoco/index.html
```

### Potential improvements
`#IMPROVEMENT-1` - I could've enhanced the hands validation by using a `RegExp` to match the two player's hands

`#IMPROVEMENT-2` - I'm not sure whether I should've designed a `TwoPairTieBreaker` given the `PairTieBreaker` may not be enough to cover all two pair scenarios

`#IMPROVEMENT-3` - I could've written unit test for this class, however, I did not think it was necessary