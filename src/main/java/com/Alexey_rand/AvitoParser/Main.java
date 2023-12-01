package com.Alexey_rand.AvitoParser;

import club.minnced.discord.webhook.send.WebhookEmbed;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        AvitoParser parser = new AvitoParser();
        parser.setup();

        parser.start();

        Runtime.getRuntime().exit(0);

        /*DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1131477469426360461/d82r_oF54wbZMxxbZ1vgoa1qoSPrkeNqTukM0P_FShHi3VgAQR1z-dRecXlNGTmX3Bus");
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        WebhookEmbed.EmbedAuthor author = new WebhookEmbed.EmbedAuthor("Alexey", "f", "f");
        WebhookEmbed.EmbedField field = new WebhookEmbed.EmbedField(true, "field1", "31231");
        embed.setAuthor("Alexey", "d", "d");

        embed.setDescription("Description embed");

        webhook.addEmbed(embed);
        webhook.execute();

         */


    }
}
