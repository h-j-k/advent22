#lang racket/base
 
(require rackunit
         "util.rkt"
         "../advent22/day01.rkt")

(define input (chunked-by-whitespace "../input/day01.txt"))

(check-equal? (part1 input) 73211)

(check-equal? (part2 input) 213958)