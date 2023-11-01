#include <iostream>
#include <discord.h> 

const std::string LOGGER_BOT_TOKEN = "bot authentication token";
const std::string STAFF_SERVER_ID = "server guild ID";
const std::string STAFF_CHANNEL_ID = "channel ID";

int main() {
    discord::Client client(LOGGER_BOT_TOKEN);

    client.on<discord::events::Ready>([](const discord::events::Ready& event) {
        std::cout << "h: " << event.user.username << "" << event.user.discriminator << std::endl;
    });

  client.run();

    return 0;
}

// God knows which library I need to use to implement this to CPP
// Also since when is discord.h a thing lol
