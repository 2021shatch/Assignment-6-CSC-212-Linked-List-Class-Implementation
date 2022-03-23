# Assignment 6: Linked List Implementation

For this assignment you will develop your own implementation of linked lists, beginning with singly linked lists and moving later to doubly linked lists.  We've arranged the instructions in an order that makes sense for incremental development and provides plenty of opportunity to test your progress along the way.  To maintain uniformity in naming, your classes should be named `SLL` and `DLL`, and your program should eventually implement the different interfaces included in the *Interfaces* folder.  We'll develop starter code together that includes stubs for the necessary methods.  (A *stub* is a short method definition that has the correct call signature and the minimal implementation required for clean compilation.)

## Phase 1: Basic List Creation & Printing

For this first phase we just want to complete a minimal set of methods so that we can create a linked list with a few elements in it, verify that it has the correct structure, and print the elements to the console (for later use in debugging).  We will need the following:

* A constructor that makes an empty list.  It should have `head` and `tail` references that are both `null`.
* A method to check if a list `isEmpty`
* Accessors for `getHead` and `getTail` (We may eventually need setters as well, but they're not necessary right away.)
* Accessors and maniupulators in the Node class to `getData`, `setData`, `getNext` and `setNext`.  (Typically LinkedList implementations don't have a `setNext` method because that could imperil the structure of the list, but because we haven't nested our Node classes we'll have to use this workaround.)
* The method `addFirst` to add a new element at the front of the list.  This will have a special case to handle if the list is initially empty.  Check the lecture slides for a discussion of how this method works.
* The method `toString` so that the list can be printed.  More details on this appear below.

In addition, we'll need to do some testing as we go to make sure everything is working as intended.  You should use the `TestCode` package we have employed before.  We suggest the following process:
* Create an empty list of strings.
* Check that `head` and `tail` are both null.  (You will need to go through the accessors if you are doing the testing in main.)
* Check that the list prints as `[]`
* Now call `addFirst` to put the letter "A" into the list.
* Check that `list.head` and `list.tail` reference the same node.
* Check that `list.tail.next` is null.
* Check that `list.head.data` is "A".
* Check that the list prints as `[A]`

If this much works, you have a working list with one element in it.  Now see what happens if you add a second and a third:
* Call `addFirst` to put the letter "B" into the list.
* Check that `list.head.next` and `list.tail` are the same.
* Check that `list.tail.next` is still null.
* Check that `list.head.data` is "B".
* Check that `list.head.next.data` is "A".
* Check that the list prints as `[B, A]`
* Call `addFirst` to put the letter "C" into the list.
* Check that `list.head.next.next` and `list.tail` are the same.
* Check that `list.tail.next` is still null.
* Check that `list.head.data` is "C".
* Check that `list.head.next.data` is "B".
* Check that `list.head.next.next.data` is "A".
* Check that the list prints as `[C, B, A]`

Now let's talk about the details of `toString`.  This will assemble and return a `String` variable representing the contents of the list.  Let's suppose that we want it to output something like this when called on the list above:

    [C, B, A]

So we need to create a variable to hold our accumulating string.  Initialize it with a bracket, then loop through all but the last element, adding them to the string with trailing commas. Finally add the last element and a closing bracket.  (If the list is empty, skip trying to add the last element and just add the closing bracket.)  Below is a `for` loop that will traverse the list, stopping just before the last element.  You can use that as a building block to construct your method.

    for (Node item = this.head; item != list.tail; item = item.next) {
      // item is a node in the list
    }

Once your `toString` method is completed, you should be able to display the contents of your list at any point in the usual way:

    System.out.println(list);

Keep in mind that if your list structure is nonstandard due to bugs in your implementation, it may give unpredictable results.  The loop might even fail to terminate.  A list that just has passed the tests outlined above should be correct, so you can use that for testing.

## Phase 2:  More List Manipulators & Miscellaneous Methods

Now that you've gotten a basic list up and running, it's time to add more methods that allow you to alter its contents in different ways.  It's worth drawing a picture of each scenario before you try to implement it.  Figure out what links need to be updated.  Think about special cases that should be handled  Then write tests for each method *before* you try to implement it.

Start with `removeFirst`, then do `addLast` and `removeLast`.  Finally do `addAfter` and `removeAfter`.  Do them one at a time, and test thoroughly before going on to the next one.  **No, really!**  You want to catch any bugs in one method before going on to the next, otherwise you'll never be sure whether a problem is in the new thing you are working on or something earlier.  Your testing should be similar to the set of tests given above in checking both the link *structure* and the *contents*.  We have provided a method to assist you here, assuming that you have correctly implemented the phase 1 methods.

This would also be a good time to add a `size` method.  It needs to loop through all the elements to count how many there are.  (Alternately, your implementation could add a field to keep track of the total number at any given time as they are added or removed...)

While working on this stage, it's pretty easy to accidentally write code that goes into an infinite loop.  If your program seems frozen, **that's a sign**.  While developing, you may want to include a print statement inside every loop so you can easily detect this sort of bug.  Only remove it (or comment out) when you are sure that things are working properly -- but don't forget to do so before you submit your work!  If you do find that your code is looping infinitely, try drawing a picture of what is happening to understand why.

## Phase 3:  Whole-List Operations

The first two phases were about building up and editing single lists.  In this phase you will add methods that split and combine lists.  These will come in two flavors, copying style and transfer style.  As in the previous phase, you should write tests for each method before you try to develop it.

For the copying style, begin by writing a **copy constructor**.  As its name indicates, this is a constructor that takes a linked list object as its sole parameter, and returns a *deep copy* of its structure.  This will be a completely separate memory structure with the same number of elements, holding the same values.  You can make it by looping through the original structure and copying a node at a time.  (Consider how you can use existing methods, e.g., `addFirst` or `addLast`.)

Next, add `subseqByCopy` and `spliceByCopy`.  Since you are making copies, these will also require looping.
* `subseqByCopy(here,n)` returns a copy of a subsequence of the list.  The subsequence starts at `here` and contains `n` nodes.  (If the original list is too short to provide the requested number of nodes, throw an exception.)  `this` remains unchanged.
* `spliceByCopy(list,afterHere)` copies the nodes of `list` and adds them to `this` following the node `afterHere`.  If `here` is null it adds them at the head of`this`.  The contents of `list` remain unchanged.

For transfer style, you will write `subseqByTransfer` and `spliceByTransfer`.  These should transfer nodes as a group from one list to another juat by updating a few links, and will not require looping.
* `subseqByTransfer(afterHere,toHere)` extracts a subsequence out of the original list and returns it as a new list (thus shortening the original list).  The extracted sequence begins with the element following `afterHere` and goes up to and including `toHere`.  The original list should skip from `afterHere` to the element that originally followed `toHere`.  If `afterHere` is `null` it should extract a sequence from the head of the list onwards.
* `spliceByTransfer(list,afterHere)` moves all the elements of `list` into `this` just after the node `afterHere`.  The input argument `list` should be left empty after the transfer.

## Phase Four:  Doubly Linked lists

For this phase you will create an implementation for doubly linked lists.  Put this in a new class called `DLL`, modeled on your `SLL` class.  You should repeat the steps of the previous three phases.  Many of the details will be the same, but you will have an extra link in each node to keep updated.  Your tests should test the links in both directions.

Some of the methods and call signatures will change due to the fact that we now have links in both directions.
* Besides `addAfter` we will have `addBefore`.
* Instead of `removeAfter` we will have `remove`.
* `subseqByTransfer` can accept `fromHere` and `toHere` as arguments, indicating the endpoints of the section to be removed.