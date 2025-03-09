### In Progress

* Fix outputting issues

### Blocked

* Generate probability map for tokenised text

### Pending

* Experiment with increasing depth
* Randomly generate probabilistic output text
* Add first order Markov dependent probability map and test
* Add second order Markov dependent probability map and test
* Add third order Markov dependent probability map and test

### Ideas

* Generalise Markov probability maps for nth order
* Implement 'fuzzing' between Markov orders to reduce 'fixation'
* Implement Markov Model Save/Load to improve program efficiency

### Done

* Implement skeleton classes for Compression/Markov with assertion flags
* Implement basic file reading and writing capabilities
* Identify used/unused characters in source text
* Find letter pair frequencies
* Find most common letter pair
* Move pair frequency logic into seperate method
* Swap most common letter pair with unused character
* Test letter pair replacement compression/decompression
* Generalise letter pair replacement for multiple compression rounds
* Experiment with compression levels and excluded characters to optimise tokenisation
* Check full compressed (unused) characters are available for compression
* Print colourised token map of compressed source text
* Encapsulate Step class into Markov class
* Join Markov class to Compress class
