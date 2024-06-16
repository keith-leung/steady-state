package smb.conf.dynreconf.utils

import com.opencsv.bean.CsvBindByPosition

class CsvResultItem {

    companion object {
        val header = "project;commit;benchmark;params;f1i1;f1i2;f1i3;f1i4;f1i5;f1i6;f1i7;f1i8;f1i9;f1i10;f1i11;f1i12;f1i13;f1i14;f1i15;f1i16;f1i17;f1i18;f1i19;f1i20;f1i21;f1i22;f1i23;f1i24;f1i25;f1i26;f1i27;f1i28;f1i29;f1i30;f1i31;f1i32;f1i33;f1i34;f1i35;f1i36;f1i37;f1i38;f1i39;f1i40;f1i41;f1i42;f1i43;f1i44;f1i45;f1i46;f1i47;f1i48;f1i49;f1i50;f2i1;f2i2;f2i3;f2i4;f2i5;f2i6;f2i7;f2i8;f2i9;f2i10;f2i11;f2i12;f2i13;f2i14;f2i15;f2i16;f2i17;f2i18;f2i19;f2i20;f2i21;f2i22;f2i23;f2i24;f2i25;f2i26;f2i27;f2i28;f2i29;f2i30;f2i31;f2i32;f2i33;f2i34;f2i35;f2i36;f2i37;f2i38;f2i39;f2i40;f2i41;f2i42;f2i43;f2i44;f2i45;f2i46;f2i47;f2i48;f2i49;f2i50;f3i1;f3i2;f3i3;f3i4;f3i5;f3i6;f3i7;f3i8;f3i9;f3i10;f3i11;f3i12;f3i13;f3i14;f3i15;f3i16;f3i17;f3i18;f3i19;f3i20;f3i21;f3i22;f3i23;f3i24;f3i25;f3i26;f3i27;f3i28;f3i29;f3i30;f3i31;f3i32;f3i33;f3i34;f3i35;f3i36;f3i37;f3i38;f3i39;f3i40;f3i41;f3i42;f3i43;f3i44;f3i45;f3i46;f3i47;f3i48;f3i49;f3i50;f4i1;f4i2;f4i3;f4i4;f4i5;f4i6;f4i7;f4i8;f4i9;f4i10;f4i11;f4i12;f4i13;f4i14;f4i15;f4i16;f4i17;f4i18;f4i19;f4i20;f4i21;f4i22;f4i23;f4i24;f4i25;f4i26;f4i27;f4i28;f4i29;f4i30;f4i31;f4i32;f4i33;f4i34;f4i35;f4i36;f4i37;f4i38;f4i39;f4i40;f4i41;f4i42;f4i43;f4i44;f4i45;f4i46;f4i47;f4i48;f4i49;f4i50;f5i1;f5i2;f5i3;f5i4;f5i5;f5i6;f5i7;f5i8;f5i9;f5i10;f5i11;f5i12;f5i13;f5i14;f5i15;f5i16;f5i17;f5i18;f5i19;f5i20;f5i21;f5i22;f5i23;f5i24;f5i25;f5i26;f5i27;f5i28;f5i29;f5i30;f5i31;f5i32;f5i33;f5i34;f5i35;f5i36;f5i37;f5i38;f5i39;f5i40;f5i41;f5i42;f5i43;f5i44;f5i45;f5i46;f5i47;f5i48;f5i49;f5i50\n"
    }

    @CsvBindByPosition(position = 0)
    lateinit var project: String

    @CsvBindByPosition(position = 1)
    lateinit var commit: String

    @CsvBindByPosition(position = 2)
    lateinit var benchmark: String

    @CsvBindByPosition(position = 3)
    lateinit var params: String

    @CsvBindByPosition(position = 4)
    private var f1i1: Double = 0.0

    @CsvBindByPosition(position = 5)
    private var f1i2: Double = 0.0

    @CsvBindByPosition(position = 6)
    private var f1i3: Double = 0.0

    @CsvBindByPosition(position = 7)
    private var f1i4: Double = 0.0

    @CsvBindByPosition(position = 8)
    private var f1i5: Double = 0.0

    @CsvBindByPosition(position = 9)
    private var f1i6: Double = 0.0

    @CsvBindByPosition(position = 10)
    private var f1i7: Double = 0.0

    @CsvBindByPosition(position = 11)
    private var f1i8: Double = 0.0

    @CsvBindByPosition(position = 12)
    private var f1i9: Double = 0.0

    @CsvBindByPosition(position = 13)
    private var f1i10: Double = 0.0

    @CsvBindByPosition(position = 14)
    private var f1i11: Double = 0.0

    @CsvBindByPosition(position = 15)
    private var f1i12: Double = 0.0

    @CsvBindByPosition(position = 16)
    private var f1i13: Double = 0.0

    @CsvBindByPosition(position = 17)
    private var f1i14: Double = 0.0

    @CsvBindByPosition(position = 18)
    private var f1i15: Double = 0.0

    @CsvBindByPosition(position = 19)
    private var f1i16: Double = 0.0

    @CsvBindByPosition(position = 20)
    private var f1i17: Double = 0.0

    @CsvBindByPosition(position = 21)
    private var f1i18: Double = 0.0

    @CsvBindByPosition(position = 22)
    private var f1i19: Double = 0.0

    @CsvBindByPosition(position = 23)
    private var f1i20: Double = 0.0

    @CsvBindByPosition(position = 24)
    private var f1i21: Double = 0.0

    @CsvBindByPosition(position = 25)
    private var f1i22: Double = 0.0

    @CsvBindByPosition(position = 26)
    private var f1i23: Double = 0.0

    @CsvBindByPosition(position = 27)
    private var f1i24: Double = 0.0

    @CsvBindByPosition(position = 28)
    private var f1i25: Double = 0.0

    @CsvBindByPosition(position = 29)
    private var f1i26: Double = 0.0

    @CsvBindByPosition(position = 30)
    private var f1i27: Double = 0.0

    @CsvBindByPosition(position = 31)
    private var f1i28: Double = 0.0

    @CsvBindByPosition(position = 32)
    private var f1i29: Double = 0.0

    @CsvBindByPosition(position = 33)
    private var f1i30: Double = 0.0

    @CsvBindByPosition(position = 34)
    private var f1i31: Double = 0.0

    @CsvBindByPosition(position = 35)
    private var f1i32: Double = 0.0

    @CsvBindByPosition(position = 36)
    private var f1i33: Double = 0.0

    @CsvBindByPosition(position = 37)
    private var f1i34: Double = 0.0

    @CsvBindByPosition(position = 38)
    private var f1i35: Double = 0.0

    @CsvBindByPosition(position = 39)
    private var f1i36: Double = 0.0

    @CsvBindByPosition(position = 40)
    private var f1i37: Double = 0.0

    @CsvBindByPosition(position = 41)
    private var f1i38: Double = 0.0

    @CsvBindByPosition(position = 42)
    private var f1i39: Double = 0.0

    @CsvBindByPosition(position = 43)
    private var f1i40: Double = 0.0

    @CsvBindByPosition(position = 44)
    private var f1i41: Double = 0.0

    @CsvBindByPosition(position = 45)
    private var f1i42: Double = 0.0

    @CsvBindByPosition(position = 46)
    private var f1i43: Double = 0.0

    @CsvBindByPosition(position = 47)
    private var f1i44: Double = 0.0

    @CsvBindByPosition(position = 48)
    private var f1i45: Double = 0.0

    @CsvBindByPosition(position = 49)
    private var f1i46: Double = 0.0

    @CsvBindByPosition(position = 50)
    private var f1i47: Double = 0.0

    @CsvBindByPosition(position = 51)
    private var f1i48: Double = 0.0

    @CsvBindByPosition(position = 52)
    private var f1i49: Double = 0.0

    @CsvBindByPosition(position = 53)
    private var f1i50: Double = 0.0

    @CsvBindByPosition(position = 54)
    private var f2i1: Double = 0.0

    @CsvBindByPosition(position = 55)
    private var f2i2: Double = 0.0

    @CsvBindByPosition(position = 56)
    private var f2i3: Double = 0.0

    @CsvBindByPosition(position = 57)
    private var f2i4: Double = 0.0

    @CsvBindByPosition(position = 58)
    private var f2i5: Double = 0.0

    @CsvBindByPosition(position = 59)
    private var f2i6: Double = 0.0

    @CsvBindByPosition(position = 60)
    private var f2i7: Double = 0.0

    @CsvBindByPosition(position = 61)
    private var f2i8: Double = 0.0

    @CsvBindByPosition(position = 62)
    private var f2i9: Double = 0.0

    @CsvBindByPosition(position = 63)
    private var f2i10: Double = 0.0

    @CsvBindByPosition(position = 64)
    private var f2i11: Double = 0.0

    @CsvBindByPosition(position = 65)
    private var f2i12: Double = 0.0

    @CsvBindByPosition(position = 66)
    private var f2i13: Double = 0.0

    @CsvBindByPosition(position = 67)
    private var f2i14: Double = 0.0

    @CsvBindByPosition(position = 68)
    private var f2i15: Double = 0.0

    @CsvBindByPosition(position = 69)
    private var f2i16: Double = 0.0

    @CsvBindByPosition(position = 70)
    private var f2i17: Double = 0.0

    @CsvBindByPosition(position = 71)
    private var f2i18: Double = 0.0

    @CsvBindByPosition(position = 72)
    private var f2i19: Double = 0.0

    @CsvBindByPosition(position = 73)
    private var f2i20: Double = 0.0

    @CsvBindByPosition(position = 74)
    private var f2i21: Double = 0.0

    @CsvBindByPosition(position = 75)
    private var f2i22: Double = 0.0

    @CsvBindByPosition(position = 76)
    private var f2i23: Double = 0.0

    @CsvBindByPosition(position = 77)
    private var f2i24: Double = 0.0

    @CsvBindByPosition(position = 78)
    private var f2i25: Double = 0.0

    @CsvBindByPosition(position = 79)
    private var f2i26: Double = 0.0

    @CsvBindByPosition(position = 80)
    private var f2i27: Double = 0.0

    @CsvBindByPosition(position = 81)
    private var f2i28: Double = 0.0

    @CsvBindByPosition(position = 82)
    private var f2i29: Double = 0.0

    @CsvBindByPosition(position = 83)
    private var f2i30: Double = 0.0

    @CsvBindByPosition(position = 84)
    private var f2i31: Double = 0.0

    @CsvBindByPosition(position = 85)
    private var f2i32: Double = 0.0

    @CsvBindByPosition(position = 86)
    private var f2i33: Double = 0.0

    @CsvBindByPosition(position = 87)
    private var f2i34: Double = 0.0

    @CsvBindByPosition(position = 88)
    private var f2i35: Double = 0.0

    @CsvBindByPosition(position = 89)
    private var f2i36: Double = 0.0

    @CsvBindByPosition(position = 90)
    private var f2i37: Double = 0.0

    @CsvBindByPosition(position = 91)
    private var f2i38: Double = 0.0

    @CsvBindByPosition(position = 92)
    private var f2i39: Double = 0.0

    @CsvBindByPosition(position = 93)
    private var f2i40: Double = 0.0

    @CsvBindByPosition(position = 94)
    private var f2i41: Double = 0.0

    @CsvBindByPosition(position = 95)
    private var f2i42: Double = 0.0

    @CsvBindByPosition(position = 96)
    private var f2i43: Double = 0.0

    @CsvBindByPosition(position = 97)
    private var f2i44: Double = 0.0

    @CsvBindByPosition(position = 98)
    private var f2i45: Double = 0.0

    @CsvBindByPosition(position = 99)
    private var f2i46: Double = 0.0

    @CsvBindByPosition(position = 100)
    private var f2i47: Double = 0.0

    @CsvBindByPosition(position = 101)
    private var f2i48: Double = 0.0

    @CsvBindByPosition(position = 102)
    private var f2i49: Double = 0.0

    @CsvBindByPosition(position = 103)
    private var f2i50: Double = 0.0

    @CsvBindByPosition(position = 104)
    private var f3i1: Double = 0.0

    @CsvBindByPosition(position = 105)
    private var f3i2: Double = 0.0

    @CsvBindByPosition(position = 106)
    private var f3i3: Double = 0.0

    @CsvBindByPosition(position = 107)
    private var f3i4: Double = 0.0

    @CsvBindByPosition(position = 108)
    private var f3i5: Double = 0.0

    @CsvBindByPosition(position = 109)
    private var f3i6: Double = 0.0

    @CsvBindByPosition(position = 110)
    private var f3i7: Double = 0.0

    @CsvBindByPosition(position = 111)
    private var f3i8: Double = 0.0

    @CsvBindByPosition(position = 112)
    private var f3i9: Double = 0.0

    @CsvBindByPosition(position = 113)
    private var f3i10: Double = 0.0

    @CsvBindByPosition(position = 114)
    private var f3i11: Double = 0.0

    @CsvBindByPosition(position = 115)
    private var f3i12: Double = 0.0

    @CsvBindByPosition(position = 116)
    private var f3i13: Double = 0.0

    @CsvBindByPosition(position = 117)
    private var f3i14: Double = 0.0

    @CsvBindByPosition(position = 118)
    private var f3i15: Double = 0.0

    @CsvBindByPosition(position = 119)
    private var f3i16: Double = 0.0

    @CsvBindByPosition(position = 120)
    private var f3i17: Double = 0.0

    @CsvBindByPosition(position = 121)
    private var f3i18: Double = 0.0

    @CsvBindByPosition(position = 122)
    private var f3i19: Double = 0.0

    @CsvBindByPosition(position = 123)
    private var f3i20: Double = 0.0

    @CsvBindByPosition(position = 124)
    private var f3i21: Double = 0.0

    @CsvBindByPosition(position = 125)
    private var f3i22: Double = 0.0

    @CsvBindByPosition(position = 126)
    private var f3i23: Double = 0.0

    @CsvBindByPosition(position = 127)
    private var f3i24: Double = 0.0

    @CsvBindByPosition(position = 128)
    private var f3i25: Double = 0.0

    @CsvBindByPosition(position = 129)
    private var f3i26: Double = 0.0

    @CsvBindByPosition(position = 130)
    private var f3i27: Double = 0.0

    @CsvBindByPosition(position = 131)
    private var f3i28: Double = 0.0

    @CsvBindByPosition(position = 132)
    private var f3i29: Double = 0.0

    @CsvBindByPosition(position = 133)
    private var f3i30: Double = 0.0

    @CsvBindByPosition(position = 134)
    private var f3i31: Double = 0.0

    @CsvBindByPosition(position = 135)
    private var f3i32: Double = 0.0

    @CsvBindByPosition(position = 136)
    private var f3i33: Double = 0.0

    @CsvBindByPosition(position = 137)
    private var f3i34: Double = 0.0

    @CsvBindByPosition(position = 138)
    private var f3i35: Double = 0.0

    @CsvBindByPosition(position = 139)
    private var f3i36: Double = 0.0

    @CsvBindByPosition(position = 140)
    private var f3i37: Double = 0.0

    @CsvBindByPosition(position = 141)
    private var f3i38: Double = 0.0

    @CsvBindByPosition(position = 142)
    private var f3i39: Double = 0.0

    @CsvBindByPosition(position = 143)
    private var f3i40: Double = 0.0

    @CsvBindByPosition(position = 144)
    private var f3i41: Double = 0.0

    @CsvBindByPosition(position = 145)
    private var f3i42: Double = 0.0

    @CsvBindByPosition(position = 146)
    private var f3i43: Double = 0.0

    @CsvBindByPosition(position = 147)
    private var f3i44: Double = 0.0

    @CsvBindByPosition(position = 148)
    private var f3i45: Double = 0.0

    @CsvBindByPosition(position = 149)
    private var f3i46: Double = 0.0

    @CsvBindByPosition(position = 150)
    private var f3i47: Double = 0.0

    @CsvBindByPosition(position = 151)
    private var f3i48: Double = 0.0

    @CsvBindByPosition(position = 152)
    private var f3i49: Double = 0.0

    @CsvBindByPosition(position = 153)
    private var f3i50: Double = 0.0

    @CsvBindByPosition(position = 154)
    private var f4i1: Double = 0.0

    @CsvBindByPosition(position = 155)
    private var f4i2: Double = 0.0

    @CsvBindByPosition(position = 156)
    private var f4i3: Double = 0.0

    @CsvBindByPosition(position = 157)
    private var f4i4: Double = 0.0

    @CsvBindByPosition(position = 158)
    private var f4i5: Double = 0.0

    @CsvBindByPosition(position = 159)
    private var f4i6: Double = 0.0

    @CsvBindByPosition(position = 160)
    private var f4i7: Double = 0.0

    @CsvBindByPosition(position = 161)
    private var f4i8: Double = 0.0

    @CsvBindByPosition(position = 162)
    private var f4i9: Double = 0.0

    @CsvBindByPosition(position = 163)
    private var f4i10: Double = 0.0

    @CsvBindByPosition(position = 164)
    private var f4i11: Double = 0.0

    @CsvBindByPosition(position = 165)
    private var f4i12: Double = 0.0

    @CsvBindByPosition(position = 166)
    private var f4i13: Double = 0.0

    @CsvBindByPosition(position = 167)
    private var f4i14: Double = 0.0

    @CsvBindByPosition(position = 168)
    private var f4i15: Double = 0.0

    @CsvBindByPosition(position = 169)
    private var f4i16: Double = 0.0

    @CsvBindByPosition(position = 170)
    private var f4i17: Double = 0.0

    @CsvBindByPosition(position = 171)
    private var f4i18: Double = 0.0

    @CsvBindByPosition(position = 172)
    private var f4i19: Double = 0.0

    @CsvBindByPosition(position = 173)
    private var f4i20: Double = 0.0

    @CsvBindByPosition(position = 174)
    private var f4i21: Double = 0.0

    @CsvBindByPosition(position = 175)
    private var f4i22: Double = 0.0

    @CsvBindByPosition(position = 176)
    private var f4i23: Double = 0.0

    @CsvBindByPosition(position = 177)
    private var f4i24: Double = 0.0

    @CsvBindByPosition(position = 178)
    private var f4i25: Double = 0.0

    @CsvBindByPosition(position = 179)
    private var f4i26: Double = 0.0

    @CsvBindByPosition(position = 180)
    private var f4i27: Double = 0.0

    @CsvBindByPosition(position = 181)
    private var f4i28: Double = 0.0

    @CsvBindByPosition(position = 182)
    private var f4i29: Double = 0.0

    @CsvBindByPosition(position = 183)
    private var f4i30: Double = 0.0

    @CsvBindByPosition(position = 184)
    private var f4i31: Double = 0.0

    @CsvBindByPosition(position = 185)
    private var f4i32: Double = 0.0

    @CsvBindByPosition(position = 186)
    private var f4i33: Double = 0.0

    @CsvBindByPosition(position = 187)
    private var f4i34: Double = 0.0

    @CsvBindByPosition(position = 188)
    private var f4i35: Double = 0.0

    @CsvBindByPosition(position = 189)
    private var f4i36: Double = 0.0

    @CsvBindByPosition(position = 190)
    private var f4i37: Double = 0.0

    @CsvBindByPosition(position = 191)
    private var f4i38: Double = 0.0

    @CsvBindByPosition(position = 192)
    private var f4i39: Double = 0.0

    @CsvBindByPosition(position = 193)
    private var f4i40: Double = 0.0

    @CsvBindByPosition(position = 194)
    private var f4i41: Double = 0.0

    @CsvBindByPosition(position = 195)
    private var f4i42: Double = 0.0

    @CsvBindByPosition(position = 196)
    private var f4i43: Double = 0.0

    @CsvBindByPosition(position = 197)
    private var f4i44: Double = 0.0

    @CsvBindByPosition(position = 198)
    private var f4i45: Double = 0.0

    @CsvBindByPosition(position = 199)
    private var f4i46: Double = 0.0

    @CsvBindByPosition(position = 200)
    private var f4i47: Double = 0.0

    @CsvBindByPosition(position = 201)
    private var f4i48: Double = 0.0

    @CsvBindByPosition(position = 202)
    private var f4i49: Double = 0.0

    @CsvBindByPosition(position = 203)
    private var f4i50: Double = 0.0

    @CsvBindByPosition(position = 204)
    private var f5i1: Double = 0.0

    @CsvBindByPosition(position = 205)
    private var f5i2: Double = 0.0

    @CsvBindByPosition(position = 206)
    private var f5i3: Double = 0.0

    @CsvBindByPosition(position = 207)
    private var f5i4: Double = 0.0

    @CsvBindByPosition(position = 208)
    private var f5i5: Double = 0.0

    @CsvBindByPosition(position = 209)
    private var f5i6: Double = 0.0

    @CsvBindByPosition(position = 210)
    private var f5i7: Double = 0.0

    @CsvBindByPosition(position = 211)
    private var f5i8: Double = 0.0

    @CsvBindByPosition(position = 212)
    private var f5i9: Double = 0.0

    @CsvBindByPosition(position = 213)
    private var f5i10: Double = 0.0

    @CsvBindByPosition(position = 214)
    private var f5i11: Double = 0.0

    @CsvBindByPosition(position = 215)
    private var f5i12: Double = 0.0

    @CsvBindByPosition(position = 216)
    private var f5i13: Double = 0.0

    @CsvBindByPosition(position = 217)
    private var f5i14: Double = 0.0

    @CsvBindByPosition(position = 218)
    private var f5i15: Double = 0.0

    @CsvBindByPosition(position = 219)
    private var f5i16: Double = 0.0

    @CsvBindByPosition(position = 220)
    private var f5i17: Double = 0.0

    @CsvBindByPosition(position = 221)
    private var f5i18: Double = 0.0

    @CsvBindByPosition(position = 222)
    private var f5i19: Double = 0.0

    @CsvBindByPosition(position = 223)
    private var f5i20: Double = 0.0

    @CsvBindByPosition(position = 224)
    private var f5i21: Double = 0.0

    @CsvBindByPosition(position = 225)
    private var f5i22: Double = 0.0

    @CsvBindByPosition(position = 226)
    private var f5i23: Double = 0.0

    @CsvBindByPosition(position = 227)
    private var f5i24: Double = 0.0

    @CsvBindByPosition(position = 228)
    private var f5i25: Double = 0.0

    @CsvBindByPosition(position = 229)
    private var f5i26: Double = 0.0

    @CsvBindByPosition(position = 230)
    private var f5i27: Double = 0.0

    @CsvBindByPosition(position = 231)
    private var f5i28: Double = 0.0

    @CsvBindByPosition(position = 232)
    private var f5i29: Double = 0.0

    @CsvBindByPosition(position = 233)
    private var f5i30: Double = 0.0

    @CsvBindByPosition(position = 234)
    private var f5i31: Double = 0.0

    @CsvBindByPosition(position = 235)
    private var f5i32: Double = 0.0

    @CsvBindByPosition(position = 236)
    private var f5i33: Double = 0.0

    @CsvBindByPosition(position = 237)
    private var f5i34: Double = 0.0

    @CsvBindByPosition(position = 238)
    private var f5i35: Double = 0.0

    @CsvBindByPosition(position = 239)
    private var f5i36: Double = 0.0

    @CsvBindByPosition(position = 240)
    private var f5i37: Double = 0.0

    @CsvBindByPosition(position = 241)
    private var f5i38: Double = 0.0

    @CsvBindByPosition(position = 242)
    private var f5i39: Double = 0.0

    @CsvBindByPosition(position = 243)
    private var f5i40: Double = 0.0

    @CsvBindByPosition(position = 244)
    private var f5i41: Double = 0.0

    @CsvBindByPosition(position = 245)
    private var f5i42: Double = 0.0

    @CsvBindByPosition(position = 246)
    private var f5i43: Double = 0.0

    @CsvBindByPosition(position = 247)
    private var f5i44: Double = 0.0

    @CsvBindByPosition(position = 248)
    private var f5i45: Double = 0.0

    @CsvBindByPosition(position = 249)
    private var f5i46: Double = 0.0

    @CsvBindByPosition(position = 250)
    private var f5i47: Double = 0.0

    @CsvBindByPosition(position = 251)
    private var f5i48: Double = 0.0

    @CsvBindByPosition(position = 252)
    private var f5i49: Double = 0.0

    @CsvBindByPosition(position = 253)
    private var f5i50: Double = 0.0

    constructor()

    fun getMap(): Map<Int, Map<Int, Double>> {
        val ret = mutableMapOf<Int, MutableMap<Int, Double>>()
        ret[1] = mutableMapOf()
        ret[2] = mutableMapOf()
        ret[3] = mutableMapOf()
        ret[4] = mutableMapOf()
        ret[5] = mutableMapOf()
        ret.getValue(1)[1] = f1i1
        ret.getValue(1)[2] = f1i2
        ret.getValue(1)[3] = f1i3
        ret.getValue(1)[4] = f1i4
        ret.getValue(1)[5] = f1i5
        ret.getValue(1)[6] = f1i6
        ret.getValue(1)[7] = f1i7
        ret.getValue(1)[8] = f1i8
        ret.getValue(1)[9] = f1i9
        ret.getValue(1)[10] = f1i10
        ret.getValue(1)[11] = f1i11
        ret.getValue(1)[12] = f1i12
        ret.getValue(1)[13] = f1i13
        ret.getValue(1)[14] = f1i14
        ret.getValue(1)[15] = f1i15
        ret.getValue(1)[16] = f1i16
        ret.getValue(1)[17] = f1i17
        ret.getValue(1)[18] = f1i18
        ret.getValue(1)[19] = f1i19
        ret.getValue(1)[20] = f1i20
        ret.getValue(1)[21] = f1i21
        ret.getValue(1)[22] = f1i22
        ret.getValue(1)[23] = f1i23
        ret.getValue(1)[24] = f1i24
        ret.getValue(1)[25] = f1i25
        ret.getValue(1)[26] = f1i26
        ret.getValue(1)[27] = f1i27
        ret.getValue(1)[28] = f1i28
        ret.getValue(1)[29] = f1i29
        ret.getValue(1)[30] = f1i30
        ret.getValue(1)[31] = f1i31
        ret.getValue(1)[32] = f1i32
        ret.getValue(1)[33] = f1i33
        ret.getValue(1)[34] = f1i34
        ret.getValue(1)[35] = f1i35
        ret.getValue(1)[36] = f1i36
        ret.getValue(1)[37] = f1i37
        ret.getValue(1)[38] = f1i38
        ret.getValue(1)[39] = f1i39
        ret.getValue(1)[40] = f1i40
        ret.getValue(1)[41] = f1i41
        ret.getValue(1)[42] = f1i42
        ret.getValue(1)[43] = f1i43
        ret.getValue(1)[44] = f1i44
        ret.getValue(1)[45] = f1i45
        ret.getValue(1)[46] = f1i46
        ret.getValue(1)[47] = f1i47
        ret.getValue(1)[48] = f1i48
        ret.getValue(1)[49] = f1i49
        ret.getValue(1)[50] = f1i50
        ret.getValue(2)[1] = f2i1
        ret.getValue(2)[2] = f2i2
        ret.getValue(2)[3] = f2i3
        ret.getValue(2)[4] = f2i4
        ret.getValue(2)[5] = f2i5
        ret.getValue(2)[6] = f2i6
        ret.getValue(2)[7] = f2i7
        ret.getValue(2)[8] = f2i8
        ret.getValue(2)[9] = f2i9
        ret.getValue(2)[10] = f2i10
        ret.getValue(2)[11] = f2i11
        ret.getValue(2)[12] = f2i12
        ret.getValue(2)[13] = f2i13
        ret.getValue(2)[14] = f2i14
        ret.getValue(2)[15] = f2i15
        ret.getValue(2)[16] = f2i16
        ret.getValue(2)[17] = f2i17
        ret.getValue(2)[18] = f2i18
        ret.getValue(2)[19] = f2i19
        ret.getValue(2)[20] = f2i20
        ret.getValue(2)[21] = f2i21
        ret.getValue(2)[22] = f2i22
        ret.getValue(2)[23] = f2i23
        ret.getValue(2)[24] = f2i24
        ret.getValue(2)[25] = f2i25
        ret.getValue(2)[26] = f2i26
        ret.getValue(2)[27] = f2i27
        ret.getValue(2)[28] = f2i28
        ret.getValue(2)[29] = f2i29
        ret.getValue(2)[30] = f2i30
        ret.getValue(2)[31] = f2i31
        ret.getValue(2)[32] = f2i32
        ret.getValue(2)[33] = f2i33
        ret.getValue(2)[34] = f2i34
        ret.getValue(2)[35] = f2i35
        ret.getValue(2)[36] = f2i36
        ret.getValue(2)[37] = f2i37
        ret.getValue(2)[38] = f2i38
        ret.getValue(2)[39] = f2i39
        ret.getValue(2)[40] = f2i40
        ret.getValue(2)[41] = f2i41
        ret.getValue(2)[42] = f2i42
        ret.getValue(2)[43] = f2i43
        ret.getValue(2)[44] = f2i44
        ret.getValue(2)[45] = f2i45
        ret.getValue(2)[46] = f2i46
        ret.getValue(2)[47] = f2i47
        ret.getValue(2)[48] = f2i48
        ret.getValue(2)[49] = f2i49
        ret.getValue(2)[50] = f2i50
        ret.getValue(3)[1] = f3i1
        ret.getValue(3)[2] = f3i2
        ret.getValue(3)[3] = f3i3
        ret.getValue(3)[4] = f3i4
        ret.getValue(3)[5] = f3i5
        ret.getValue(3)[6] = f3i6
        ret.getValue(3)[7] = f3i7
        ret.getValue(3)[8] = f3i8
        ret.getValue(3)[9] = f3i9
        ret.getValue(3)[10] = f3i10
        ret.getValue(3)[11] = f3i11
        ret.getValue(3)[12] = f3i12
        ret.getValue(3)[13] = f3i13
        ret.getValue(3)[14] = f3i14
        ret.getValue(3)[15] = f3i15
        ret.getValue(3)[16] = f3i16
        ret.getValue(3)[17] = f3i17
        ret.getValue(3)[18] = f3i18
        ret.getValue(3)[19] = f3i19
        ret.getValue(3)[20] = f3i20
        ret.getValue(3)[21] = f3i21
        ret.getValue(3)[22] = f3i22
        ret.getValue(3)[23] = f3i23
        ret.getValue(3)[24] = f3i24
        ret.getValue(3)[25] = f3i25
        ret.getValue(3)[26] = f3i26
        ret.getValue(3)[27] = f3i27
        ret.getValue(3)[28] = f3i28
        ret.getValue(3)[29] = f3i29
        ret.getValue(3)[30] = f3i30
        ret.getValue(3)[31] = f3i31
        ret.getValue(3)[32] = f3i32
        ret.getValue(3)[33] = f3i33
        ret.getValue(3)[34] = f3i34
        ret.getValue(3)[35] = f3i35
        ret.getValue(3)[36] = f3i36
        ret.getValue(3)[37] = f3i37
        ret.getValue(3)[38] = f3i38
        ret.getValue(3)[39] = f3i39
        ret.getValue(3)[40] = f3i40
        ret.getValue(3)[41] = f3i41
        ret.getValue(3)[42] = f3i42
        ret.getValue(3)[43] = f3i43
        ret.getValue(3)[44] = f3i44
        ret.getValue(3)[45] = f3i45
        ret.getValue(3)[46] = f3i46
        ret.getValue(3)[47] = f3i47
        ret.getValue(3)[48] = f3i48
        ret.getValue(3)[49] = f3i49
        ret.getValue(3)[50] = f3i50
        ret.getValue(4)[1] = f4i1
        ret.getValue(4)[2] = f4i2
        ret.getValue(4)[3] = f4i3
        ret.getValue(4)[4] = f4i4
        ret.getValue(4)[5] = f4i5
        ret.getValue(4)[6] = f4i6
        ret.getValue(4)[7] = f4i7
        ret.getValue(4)[8] = f4i8
        ret.getValue(4)[9] = f4i9
        ret.getValue(4)[10] = f4i10
        ret.getValue(4)[11] = f4i11
        ret.getValue(4)[12] = f4i12
        ret.getValue(4)[13] = f4i13
        ret.getValue(4)[14] = f4i14
        ret.getValue(4)[15] = f4i15
        ret.getValue(4)[16] = f4i16
        ret.getValue(4)[17] = f4i17
        ret.getValue(4)[18] = f4i18
        ret.getValue(4)[19] = f4i19
        ret.getValue(4)[20] = f4i20
        ret.getValue(4)[21] = f4i21
        ret.getValue(4)[22] = f4i22
        ret.getValue(4)[23] = f4i23
        ret.getValue(4)[24] = f4i24
        ret.getValue(4)[25] = f4i25
        ret.getValue(4)[26] = f4i26
        ret.getValue(4)[27] = f4i27
        ret.getValue(4)[28] = f4i28
        ret.getValue(4)[29] = f4i29
        ret.getValue(4)[30] = f4i30
        ret.getValue(4)[31] = f4i31
        ret.getValue(4)[32] = f4i32
        ret.getValue(4)[33] = f4i33
        ret.getValue(4)[34] = f4i34
        ret.getValue(4)[35] = f4i35
        ret.getValue(4)[36] = f4i36
        ret.getValue(4)[37] = f4i37
        ret.getValue(4)[38] = f4i38
        ret.getValue(4)[39] = f4i39
        ret.getValue(4)[40] = f4i40
        ret.getValue(4)[41] = f4i41
        ret.getValue(4)[42] = f4i42
        ret.getValue(4)[43] = f4i43
        ret.getValue(4)[44] = f4i44
        ret.getValue(4)[45] = f4i45
        ret.getValue(4)[46] = f4i46
        ret.getValue(4)[47] = f4i47
        ret.getValue(4)[48] = f4i48
        ret.getValue(4)[49] = f4i49
        ret.getValue(4)[50] = f4i50
        ret.getValue(5)[1] = f5i1
        ret.getValue(5)[2] = f5i2
        ret.getValue(5)[3] = f5i3
        ret.getValue(5)[4] = f5i4
        ret.getValue(5)[5] = f5i5
        ret.getValue(5)[6] = f5i6
        ret.getValue(5)[7] = f5i7
        ret.getValue(5)[8] = f5i8
        ret.getValue(5)[9] = f5i9
        ret.getValue(5)[10] = f5i10
        ret.getValue(5)[11] = f5i11
        ret.getValue(5)[12] = f5i12
        ret.getValue(5)[13] = f5i13
        ret.getValue(5)[14] = f5i14
        ret.getValue(5)[15] = f5i15
        ret.getValue(5)[16] = f5i16
        ret.getValue(5)[17] = f5i17
        ret.getValue(5)[18] = f5i18
        ret.getValue(5)[19] = f5i19
        ret.getValue(5)[20] = f5i20
        ret.getValue(5)[21] = f5i21
        ret.getValue(5)[22] = f5i22
        ret.getValue(5)[23] = f5i23
        ret.getValue(5)[24] = f5i24
        ret.getValue(5)[25] = f5i25
        ret.getValue(5)[26] = f5i26
        ret.getValue(5)[27] = f5i27
        ret.getValue(5)[28] = f5i28
        ret.getValue(5)[29] = f5i29
        ret.getValue(5)[30] = f5i30
        ret.getValue(5)[31] = f5i31
        ret.getValue(5)[32] = f5i32
        ret.getValue(5)[33] = f5i33
        ret.getValue(5)[34] = f5i34
        ret.getValue(5)[35] = f5i35
        ret.getValue(5)[36] = f5i36
        ret.getValue(5)[37] = f5i37
        ret.getValue(5)[38] = f5i38
        ret.getValue(5)[39] = f5i39
        ret.getValue(5)[40] = f5i40
        ret.getValue(5)[41] = f5i41
        ret.getValue(5)[42] = f5i42
        ret.getValue(5)[43] = f5i43
        ret.getValue(5)[44] = f5i44
        ret.getValue(5)[45] = f5i45
        ret.getValue(5)[46] = f5i46
        ret.getValue(5)[47] = f5i47
        ret.getValue(5)[48] = f5i48
        ret.getValue(5)[49] = f5i49
        ret.getValue(5)[50] = f5i50
        return ret
    }

    fun getKey(): CsvLineKey {
        return CsvLineKey(project, commit, benchmark, params)
    }
}