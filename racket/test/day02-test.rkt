#lang racket/base

(require rackunit
         racket/file
         "../advent22/day02.rkt")

(define input (file->lines "../input/day02.txt"))

(check-equal? (part1 input) 10310)

(check-equal? (part2 input) 14859)