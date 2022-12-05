#lang racket/base

(require (prefix-in data: data/collection)
         racket/list
         racket/set)

(define (part1 input)
  (foldr (lambda (v r) (+ (find-common v) r)) 0 input))

(define (part2 input)
  (let ([groups (data:sequence->list* (data:chunk 3 input))])
  (foldr (lambda (v r) (+ (find-common-in-group v) r)) 0 groups)))

(define (find-common line)
  (define letters (string->list line))
  (let-values ([(x y) (split-at letters (/ (length letters) 2))])
  (to-priority (first (set-intersect x y)))))

(define (find-common-in-group group)
  (define group-list (map string->list group))
  (to-priority (first (foldr (lambda (v r) (set-intersect v r)) (first group-list) (rest group-list)))))

(define (to-priority letter)
  (let ([val (char->integer letter)])
    (if (< val 97) (- val 38) (- val 96))))

(provide part1
         part2)