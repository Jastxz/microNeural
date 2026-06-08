# Graph Report - .  (2026-06-03)

## Corpus Check
- Corpus is ~7,572 words - fits in a single context window. You may not need a graph.

## Summary
- 63 nodes · 89 edges · 15 communities (5 shown, 10 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- [[_COMMUNITY_Community 0|Community 0]]
- [[_COMMUNITY_Community 1|Community 1]]
- [[_COMMUNITY_Community 2|Community 2]]
- [[_COMMUNITY_Community 3|Community 3]]
- [[_COMMUNITY_Community 4|Community 4]]
- [[_COMMUNITY_Community 5|Community 5]]
- [[_COMMUNITY_Community 6|Community 6]]
- [[_COMMUNITY_Community 7|Community 7]]
- [[_COMMUNITY_Community 8|Community 8]]
- [[_COMMUNITY_Community 9|Community 9]]
- [[_COMMUNITY_Community 10|Community 10]]
- [[_COMMUNITY_Community 11|Community 11]]
- [[_COMMUNITY_Community 12|Community 12]]
- [[_COMMUNITY_Community 13|Community 13]]
- [[_COMMUNITY_Community 14|Community 14]]

## God Nodes (most connected - your core abstractions)
1. `RequestsLimitFilter` - 9 edges
2. `NeuralController` - 6 edges
3. `MundoRequest` - 6 edges
4. `ResponseEntity` - 5 edges
5. `PostMapping` - 4 edges
6. `ServletResponse` - 3 edges
7. `GlobalRequest` - 3 edges
8. `String` - 3 edges
9. `UserRequest` - 3 edges
10. `MicroNeuralApplication` - 2 edges

## Surprising Connections (you probably didn't know these)
- `RequestsLimitFilter` --implements--> `Filter`  [EXTRACTED]
  src/main/java/es/jastxz/micro_neural/components/RequestsLimitFilter.java →   _Bridges community 3 → community 2_

## Import Cycles
- None detected.

## Communities (15 total, 10 thin omitted)

### Community 0 - "Community 0"
Cohesion: 0.49
Nodes (5): NeuralController, MundoRequest, PostMapping, ResponseEntity, String

### Community 1 - "Community 1"
Cohesion: 0.47
Nodes (4): Around, LoggerAspect, Object, ProceedingJoinPoint

### Community 2 - "Community 2"
Cohesion: 0.40
Nodes (4): Filter, GlobalRequestRepo, ServletRequest, UserRequestRepo

### Community 3 - "Community 3"
Cohesion: 0.40
Nodes (3): RequestsLimitFilter, HttpServletRequest, String

### Community 4 - "Community 4"
Cohesion: 0.50
Nodes (3): FilterChain, Override, ServletResponse

## Knowledge Gaps
- **11 isolated node(s):** `String`, `Object`, `Override`, `Configuration`, `String` (+6 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **10 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `RequestsLimitFilter` connect `Community 3` to `Community 2`, `Community 4`, `Community 5`?**
  _High betweenness centrality (0.031) - this node is a cross-community bridge._
- **Why does `UserRequest` connect `Community 9` to `Community 2`, `Community 3`?**
  _High betweenness centrality (0.024) - this node is a cross-community bridge._
- **Why does `GlobalRequest` connect `Community 5` to `Community 2`?**
  _High betweenness centrality (0.023) - this node is a cross-community bridge._
- **What connects `String`, `Object`, `Override` to the rest of the system?**
  _11 weakly-connected nodes found - possible documentation gaps or missing edges._