#include <iostream>
#include <curl.h> 

const std::string WEBHOOK_URL = "funni webhook url here";

void DiscordEventLogger(const std::string& message) {
    CURL* curl = curl_easy_init();
    if (curl) {
        CURLcode res;
        curl_easy_setopt(curl, CURLOPT_URL, WEBHOOK_URL.c_str());

        struct curl_slist* headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/json");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        std::string json_data = "{\"content\":\"" + message + "\"}";
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, json_data.c_str());

        res = curl_easy_perform(curl);
        curl_easy_cleanup(curl);
    }
}

int main() {
    DiscordEventLogger("happy birthday alaa");

    return 0;
}

// h
