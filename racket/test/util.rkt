#lang racket/base

(require racket/file
         racket/string)

(define (chunked-by-whitespace path)
  (map (lambda (chunk) (string-split chunk "\n")) (string-split (file->string path) "\n\n")))

(provide chunked-by-whitespace)