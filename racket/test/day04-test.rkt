#lang racket/base

(require rackunit
         racket/file
         "../advent22/day04.rkt")

(define input (file->lines "../input/day04.txt"))

(check-equal? (part1 input) 644)

(check-equal? (part2 input) 926)