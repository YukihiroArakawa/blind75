# 271. Encode and Decode Strings

Difficulty: Medium

## Problem

Design an algorithm to encode a list of strings to a single string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Implement the `encode` and `decode` methods.

You may not use any built-in serialization method such as `eval`, `serialize`, or language-specific helpers that directly convert lists to strings and back.

The algorithm should handle arbitrary strings, including:

- empty strings
- strings containing digits
- strings containing delimiter-like characters

## Examples

### Example 1

**Input:** `["lint","code","love","you"]`  
**Output:** `["lint","code","love","you"]`

### Example 2

**Input:** `["we","say",":","yes"]`  
**Output:** `["we","say",":","yes"]`

## Constraints

- `1 <= strs.length <= 200`
- `0 <= strs[i].length <= 200`
- `strs[i]` may contain any valid ASCII character.
