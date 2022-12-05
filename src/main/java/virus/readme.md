# Virus code challenge
## Opgavebeskrivelse
Du gives et n x n grid bestående af 3 forskellige entiteter:
- 0 (tom plads)
- V (virus)
- A (anti-virus)

Et 4 x 4 grid kan f.eks. se sådan ud:

```
0 0 A 0
V V 0 0
A A V A
0 0 A A
```

For et koordinat (x,y) i koordinatsystmet skal der bestemmes om der skabes en infektion. Det sker hvis:
- koordinatet består af et virus
- virusset ikke er under kontrol 

*Et virus er under kontrol såfremt det (og dets virus-naboer) er omringet af anti-virus (diagonaler medregnes ikke).*

## Eksempler
### Input:
```
0 0 0 0
V V V 0
0 0 V 0
0 0 0 0
```

`(1,2)`

### Forventet output:
`true`

#### Forklaring
`(1,2)` er en virus og danner formation med `(0,2)`, `(2,2)` og `(2,1)`. Rundt om findes tomme pladser.

### Input:
```
0 A A 0
A V V A
0 A V A
0 0 A 0
```

`(1,2)`

### Forventet output:
`false`

#### Forklaring
`(1,2)` er en virus og danner formation med `(2,2)` og `(2,1)`. Disse felter er tilsammen omringet af anti-virus.
