# Web Application in Clojure (Ring as adapter and compojure to properly manage the routers and corresponding handlers)

## Why Clojure for Webapp?
I have got my friends constantly asking, why Clojure for web-app! I wouldn't answer it until they atleast get to know what clojure is, or atleast gooooogle! I always learned from my colleagues and mentors - the one thing - for last couple of years - not everyone wants popular thing to solve their problem, nor popular things are not always the best to solve their problem!

http://practicalli.github.io/clojure-webapps/introducing-ring/

A Clojure library designed to demonstrate a webapp using Ring and Compojure.

## Pre-requisites:
* Knowledge in Clojure

## Read through (Refer to images in the images folder)
* Ring - an adapter to convert the requests to clojure map anddeliver the response as another clojure map. The response comes through
handler which responds corresponding to the routes. (uri)

* Middleware
  http://practicalli.github.io/clojure-webapps/middleware-in-ring/
  http://practicalli.github.io/clojure-webapps/middleware-in-ring/wrap-reload.html
  Example: Wrap reload function enables you to change the source code especially in the handler side without 
  restarting the server. Usually we create another profile known as dev with another main function that handles
  the handler through the wrap/reload middleware provided by Ring. We use this only in the dev environment.

* Compojure
  A component that makes your life easy by providing you a template for you to define various routes (the URI passed)
  and corresponding handlers.So basically the handler that gets played depends on the end point passed, and the whole process 
  is easily handled when you use the framework compojure.
  
  """
  To make our webapp more useful we will add more functionality, which will require more routes. We can use a library called 
  Compojure to help us easily define routes and their associated handlers."""

## Basic Concept

Routes are defined by `http method` with its arguments, and the URI. So we have to define routes first and then provide the handler there itself.
Obviously the arguments being passed along with HTTP method and URI will then be the arguments of the functions that are acting as handlers.

* Example:

```
(defroutes myapp
  (GET "/hello/:name" [name] (str "Hello " name)))

```

## Basic concept of Macros, as we are using hell lot of macros here

When you write (+ 1 2), the reader reads the forms + symbol, and the 1 and 2 forms and then convert to actual datastructures of clojure.
This is then passed to the evaluator, and evaluator finds out the actual additional functionality corresponding to + symbol and applies the
arguments (nodes of abstract syntax tree in this case) and applies it and give bytecode which machine then evaluates.


Now when you give (1 + 2), it fails because reader creates an intermediate structure (tree) that evaluator fails to evaluate.

However you can tweak something here. Asking the reader to do one more step before the structure is passed to evaluator. Let us call this
macro expander. It expands the intermediate `incorrect` data structure to proper structure, which is then passed to the evaluator. You can consider
this as manipualting 1 + and 2 symbols to represent a proper form. For this first you need to define a function

```
;; defining a macro infix, which tells the compiler. Hey my sweet Reader of the compiler! First create the structure for this macro... infix -> 1 -> (+) and 2
;; remember its a macro. Please expand the macro, to form the proper structure: + -> 1 and 2, and then passed to the evaluator. The infix macro does the magical job
;; converting the structure (basically the code) you passed to actual form. You can also call (macro-expand '(infix (1 + 2))) to see what is it expanding to before
;; getting evaluated. You can see jargon there, but the jargons corresponding to (+ 1 2) and not of (1 + 2)

(defmacro infix 
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed))) 


```
