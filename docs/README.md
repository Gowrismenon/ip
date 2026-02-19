# Mal User Guide
"Long live ~~evil~~ your productivity!
Mal is a task management chatbot designed to keep your life from falling into total chaos. She's sassy but helpful, making sure you stay on track.

##Introduction 
Mal helps you track your tasks using a simple command-line-style interface inside a custom JavaFX GUI. Whether you're plotting world domination or just need to finish your homework, Mal has your back.


## Core features
### Todo tasks 
Adds general tasks that don't have a specific tmeframe
- **Command** : todo <description>
- **Example** : todo Steal Maleficient's magic sceptor
```
expected output:
Added:
[T][ ] Steal Maleficient's magic sceptor
Now you have 1 tasks for world domination
```

### Deadline 
Adds tasks that with a deadline
- **Command** : deadline <description> /by <deadline>
- **Example** : deadline steal the magic wand /by coronation
```
expected output:
Added:
[D][ ] steal the magic wand (by: coronation)
Now you have 2 tasks for world domination
```
### Event tracking 
Adds tasks that have  a specific tmeframe
- **Command** : event <description> /from <start> /to <end>
- **Example** : event Cottilion /from today /to tmr
```
expected output:
Added:
[E][ ] Cottilion (from:today to:tmr)
Now you have 3 tasks for world domination
```

### List 
Lists out the tasks You have to do
- **Command** : list
With no tasks:
```
expected output:
There is nothing to show, genius. Add some tasks!
```
With tasks:
```
expected output:
Here is the master plan, I guess:
1. [T][ ] Steal Maleficient's magic sceptor
2. [D][ ] steal the magic wand (by: coronation)
3. [E][ ] Cottilion (from:today to:tmr)
```

### Find
Finds all tasks containing a specific word/name
- **Command** : find <name>
- **Example**: find Magic
```
expected output:
Here is what you want s:
1. [T][ ] Steal Maleficient's magic sceptor
2. [D][ ] steal the magic wand (by: coronation)
```
### Mark
Marks the task at a specific index as done 
- **Command** : mark <index>
- **Example**: mark 1
```
expected output:
Alright..Now we're getting somewhere!
[T][X] Steal Maleficient's magic sceptor
```
### Umark
Marks the task at a specific index as incomplete
- **Command** : unmark <index>
- **Example**: unmark 1
```
expected output:
Oh boohoo, we're reopening old wounds 
[T][ ] Steal Maleficient's magic sceptor
```
### Delete
Deletes the task at the index from the list
- **Command** : delete <index>
- **Example**: delete 1
if the task isn't done:
```
expected output:
Deleted:
[T][ ] Steal Maleficient's magic sceptor
Let's call that a strategic decision, hm?
```
If the task is done:
```
expected output:
Right, that was inevitable
Deleted:[T][ ] Steal Maleficient's magic sceptor
```
### Bye
Completes conversation and saves the list for future use 
- **Command** : bye
