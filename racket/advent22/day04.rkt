#lang racket/base

(require (prefix-in data: data/collection)
         racket/list
         racket/match
         racket/set
         (prefix-in seq: seq))

(define (part1 input)
  (count has-subset-1? input))


(define (part2 input)
  (count has-subset-2? input))

(define (has-subset-1? line)
  (match-define (list a1 a2 b1 b2) (map string->number (rest (regexp-match #rx"([0-9]+)-([0-9]+),([0-9]+)-([0-9]+)" line))))
  (let ([a (in-inclusive-range a1 a2)] [b (in-inclusive-range b1 b2)])
    (or (seq:contains? a b) (seq:contains? b a))))

(define (has-subset-2? line)
  (match-define (list a1 a2 b1 b2) (map string->number (rest (regexp-match #rx"([0-9]+)-([0-9]+),([0-9]+)-([0-9]+)" line))))
  (let ([a (data:sequence->list* (in-inclusive-range a1 a2))] [b (data:sequence->list* (in-inclusive-range b1 b2))])
    (> (length (set-intersect a b)) 0)))

(provide part1
         part2)