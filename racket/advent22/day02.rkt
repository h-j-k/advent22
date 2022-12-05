#lang racket/base

(require racket/match)

(define (part1 input)
  (foldr (lambda (v r) (+ (rps-1 v) r)) 0 input))

(define (part2 input)
  (foldr (lambda (v r) (+ (rps-2 v) r)) 0 input))

(define (rps-1 line)
  (match line
    ["A X" 4]   ; 1+3
    ["A Y" 8]   ; 2+6
    ["A Z" 3]   ; 3+0
    ["B X" 1]   ; 1+0
    ["B Y" 5]   ; 2+3
    ["B Z" 9]   ; 3+6
    ["C X" 7]   ; 1+6
    ["C Y" 2]   ; 2+0
    ["C Z" 6])) ; 3+3

(define (rps-2 line)
  (match line
    ["A X" 3]   ; 3+0
    ["A Y" 4]   ; 1+3
    ["A Z" 8]   ; 2+6
    ["B X" 1]   ; 1+0
    ["B Y" 5]   ; 2+3
    ["B Z" 9]   ; 3+6
    ["C X" 2]   ; 2+0
    ["C Y" 6]   ; 3+3
    ["C Z" 7])) ; 1+6

(provide part1
         part2)