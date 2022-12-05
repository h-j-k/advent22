#lang racket/base

(require racket/list)

(define (part1 input)
  (sum-top input 1))

(define (part2 input)
  (sum-top input 3))

(define (sum-top input n)
  (apply + (take (sort (map sum-calories input) >) n)))

(define (sum-calories chunk)
  (apply + (map string->number chunk)))

(provide part1
         part2)