# iai-state-space-search
Java implementation of BFS, UCS and A* search algorithms as part of Introduction to Artificial Intelligence course at Faculty of Electrical Engineering and Computing, University of Zagreb, academic year 2022./2023.

<br />

**Input arguments:**

- **'--alg'** - search algorithm (**UCS**, **BFS** or **A*** algorithms are supported)

- **'--ss'** - relative path to states file

- **'--h'** - relative path to heuristic file (only for **A***)

- **'--check-optimistic'** - optional, check if given heuristic is optimistic (only for **A***)

- **'--check-consistent'** - optional, check if given heuristic is consistent (only for **A***)

<br />

**States file:**

- lines starting with **'#'** are comments and are ignored

**'<starting_state>'** - 1st row

**'<goal_states>'** - 2nd row, multiple goal states must be separated by **','**

**'\<state>: <adjacent_state>,\<cost_value>'** - all other rows, multiple adjacent states are seperated by space **' '**

<br />

**Heuristic file:**

- lines starting with **'#'** are comments and are ignored

**'\<state>: \<heuristic_value>'** - all rows are of this format
