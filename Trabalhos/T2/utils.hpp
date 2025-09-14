#if !defined(UTILS_HPP)
#define UTILS_HPP

#include <vector>
#include <string>

using namespace std;

vector<string> parseCSVLine(const string &line);
string iso_8859_1_to_utf8(string &str);
string numberFormat(int n);
string percentFormat(double value);

#endif