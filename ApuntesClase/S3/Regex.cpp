#include <iostream>
#include <regex>

bool validateDNI(const std::string& dni) {
    // Regular expression pattern for DNI validation
    std::regex pattern("^\\d{8}$");

    // Check if the DNI matches the pattern
    return std::regex_match(dni, pattern);
}

int main() {
    std::string dni;
    std::cout << "Enter a DNI number (8 digits): ";
    std::cin >> dni;

    if (validateDNI(dni)) {
        std::cout << "Valid DNI number." << std::endl;
    } else {
        std::cout << "Invalid DNI number." << std::endl;
    }

    return 0;
}