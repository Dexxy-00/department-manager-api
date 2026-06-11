# JPA & Hibernate Notes

## The Persistence Context (PC)

The Persistence Context is a temporary, in-memory cache that Hibernate uses to track entities during a transaction. It is **not** the database.

- An entity inside the PC is called **"managed"**.
- An entity outside the PC is called **"detached"**.

The PC is created when a `@Transactional` method begins and is destroyed when it ends.

### `@Transactional` on a Service Method

When a service method is marked with `@Transactional`, a single PC lives for the entire method call.

1. **`findById()`** is called -> The entity is fetched and becomes **managed**.
2. You modify the entity object in your Java code.
3. Because the entity is still managed, the PC detects this change and marks the entity as **"dirty"**.
4. When the method ends, the transaction commits, and Hibernate automatically sends an `UPDATE` SQL statement for any dirty entities.