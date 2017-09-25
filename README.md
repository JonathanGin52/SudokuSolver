# SudokuSolver
A backtracking sudoku algorithm that is able to solve n x n sized sudoku puzzles (where n is a perfect square).

Puzzles are inputted as a string with a length of n^2. 
* Unknown values are denoted by 0
* For numbers greater than 9, use capital letters.
  * 10 = A, 11 = B, 12 = C ... 34 = Y, 35 = Z

### Example inputs
4x4 puzzle example
> 0130200000030210

9x9 puzzle example
> 010020300004005060070000008006900070000100002030048000500006040000800106008000000

16x16 puzzle example
> 0004E00B0GA00000GFB02070000000003670GF00D00000C82001009007063D0BF9C0B10A8000000600E000000051C00FDA00000000000310B0080000C00G000E0D3CA509704000000000800E0FGA915000000CB0000D0A30080600070C0E002400000G060A000F4070003005E000G000000G00000810AC020B10F00000200009

25x25 puzzle example
> 00C60070I05O0A1004000000020J0D000A00000000I50000010000000M000030200EC0G8P000G0002N00DCM000LFJ30000E0N0O00000P840GJL0070003C0904020000000A0OCHG00050000009006P0008053000000K00IJF0AB000ICJ0000000N00700400000000E0M00IGK06BD0000000M0P001H54700E083L00B00060KDF00000090020P0180050L0010000GA07004K00900E0O0H0P2500000D00000M00000J1800007L00C02H000I6G00F00D0A08AICG900050000J00H0L0F00M0800F03060L0070IE50100000000J010GB000AMPF000000L000310L004000020D0OP00E00600000000F0CE06HO0000000D0005NG40DO720900F30M000000800PK20J000010000L300C0000GC050BL0N00F0000J900000PA00009KM74030EPI0B0000010FO060M80PE0AB090K1G070N00DEDL100500060M0NA000200I7B
