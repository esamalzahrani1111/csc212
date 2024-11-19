# Public | عام

## CSC 212 Project - Fall 2024

### A Simple Search Engine

**Due date**: 21/11/2024  
**Marks**: 10

---

## Project Overview

This project aims to develop a basic search engine capable of indexing, retrieving, and ranking documents based on given queries. The core data structures utilized will be lists and inverted indices. The engine will support simple Boolean queries (`AND`, `OR`) and incorporate term frequency for relevance ranking. The focus is on using ADT List to build the index and inverted index for building the search engine. Finally, use Binary Search Trees (BSTs) to enhance the inverted index performance.

---

## Data Structures and Algorithms

- **Index**: A mapping from document IDs to a list of words contained in the document.
- **Inverted Index**: A mapping from terms (unique words) to a list of documents containing those terms. Both Index and Inverted Index will be implemented using a list of lists.
- **Inverted Index with BSTs**: Enhance the implementation of Inverted Index by using BSTs instead of lists.
- **Lists**: Represent the document collection, the vocabulary, and the lists of documents associated with each term in the inverted index.
- **BSTs**: Enhance the search for an item in the collection, making it look up items in logarithmic time.

---

## Problem Statements

### 1. Document Processing

- Read documents from a CSV file.
- Split the text into a list of words based on whitespace.
- Convert all text to lowercase.
- Preprocess the text by removing stop-words (e.g., "the," "is," "and"). A list of stop-words will be provided.
- Remove all punctuations and non-alphanumerical characters.
- Build the index.

### 2. Indexing

- Create an index mapping document IDs to lists of words.
- Create the Inverted Index mapping words to lists of document IDs.
- Enhance the Inverted Index using BSTs for improved performance.

### 3. Query Processing

- Support simple Boolean queries (`AND`, `OR`).
- Use list intersection for `AND` queries to find documents containing all terms.
- Use list union for `OR` queries to find documents containing at least one term.
- Analyze performance of Boolean operations across indices and document your findings.

### 4. Ranking

- Implement term frequency (TF) to rank documents based on query term frequency.
- Sum scores for all query terms for each document and rank results in descending order.

---

## Understanding Indexes

An Index maps documents to words but is not efficient for search engines. The inverted index maps terms to lists of documents containing those terms, making it a fundamental structure for search engines.

### How it Works

1. **Tokenization**: Documents are broken down into individual words or tokens.
2. **Indexing**: Each word is associated with a list of documents that contain it.
3. **Inverted Index Creation**: A data structure maps words to their corresponding lists of documents.

---

### Example for Boolean Retrieval using Inverted Index

**Documents**:

1. "The national flag of the Kingdom of Saudi Arabia color is green and white."  
2. "The green color extends from the pole to the end of the flag."  
3. "The bright green color known as emerald green."  
4. "The flag of Saudi Arabia has an Arabic shahada and a sword in snow white."

### Example Inverted Index

| Word         | Documents       |
|--------------|-----------------|
| National     | [1]            |
| Flag         | [1, 2, 4]      |
| Kingdom      | [1]            |
| Saudi        | [1, 4]         |
| Arabia       | [1, 4]         |
| Color        | [1, 2, 3]      |
| Green        | [1, 2, 3]      |
| White        | [1, 4]         |
| Extends      | [2]            |
| Pole         | [2]            |
| End          | [2]            |
| Bright       | [3]            |
| Known        | [3]            |
| Emerald      | [3]            |
| Arabic       | [4]            |
| Shahada      | [4]            |
| Sword        | [4]            |
| Snow         | [4]            |

---

### Query Examples

#### Input:
`color AND green AND white`

#### Output:
- Document 1: "The national flag of the Kingdom of Saudi Arabia color is green and white."

#### Input:
`green OR shahada`

#### Output:
- Document 1: "The national flag of the Kingdom of Saudi Arabia color is green and white."
- Document 2: "The green color extends from the pole to the end of the flag."
- Document 3: "The bright green color known as emerald green."
- Document 4: "The flag of Saudi Arabia has an Arabic shahada and a sword in snow white."

---

### Ranking Example

**Query**: `Data structures`

| Document     | Term Frequency       | Score |
|--------------|----------------------|-------|
| Document 1   | (Data: 1) + (Structures: 2) = 3 | 3     |
| Document 2   | (Data: 1) + (Structures: 0) = 1 | 1     |

**Ranked Results**:
1. Document 1: Score = 3
2. Document 2: Score = 1

---

## Deliverables

1. **Index**: Map document IDs to words using ADT List.
2. **Inverted Index**: Efficient implementation using ADT List.
3. **Inverted Index with BST**: Use BST to enhance query performance.
4. **Analysis**: Performance comparison between Index, Inverted Index, and BST.
5. **Query Processor**: Module for processing Boolean and Ranked Retrieval queries.
6. **Ranking Algorithm**: Implementation of term frequency.
7. **User Interface**: Basic interface for querying and viewing results.
8. **Documentation**: Explanation of design, implementation, and usage.
9. **Class Diagram**: Project design with classes, methods, and interactions.
10. **Test Menu**:
    - Boolean Retrieval: Enter Boolean queries.
    - Ranked Retrieval: Enter queries for ranked results.
    - Indexed Documents: Display number of indexed documents.
    - Indexed Tokens: Display vocabulary and token counts.

---

## Additional Considerations

- **Efficiency**: Focus on data structure efficiency for large datasets.
- **Stop Word List**: Provided for preprocessing.
- **Lowercase Letters**: Normalize text for consistency.
- **Boolean Ranking**: Outputs unranked results.
- **Relevance Ranking**: Efficient term frequency storage and processing.
- **Index Design**: Unified structure for Boolean and Ranked Retrieval.

---

## Search Process

1. **Query Parsing**: Break down the query into terms.
2. **Term Lookup**: Search terms in the inverted index.
3. **Document Retrieval**: Retrieve document lists for each term.
4. **Boolean Operation**: Combine lists for Boolean queries.
5. **Ranking**: Rank results based on relevance.

---

### Bonus

- Implement a more efficient data structure than BSTs.
- Use a GitHub private repo for collaboration.

---

### Rules

- All data structures must be implemented by students.
- Usage of external libraries is prohibited.
- Plagiarism checks will be conducted, and penalties applied if detected.
- A maximum of three team members is allowed per project.

---
