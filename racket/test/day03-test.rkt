#lang racket/base

(require rackunit
         racket/file
         "../advent22/day03.rkt")

(define input (file->lines "../input/day03.txt"))

(check-equal? (part1 input) 7691)

(check-equal? (part2 input) 2508)