all: \
  disc1.1.tail-pad.48000hz_24bit_2ch.wav \
  disc1.2.tail-pad.48000hz_24bit_2ch.wav \
  disc2.1.tail-pad.48000hz_24bit_2ch.wav \
  disc2.2.tail-pad.48000hz_24bit_2ch.wav \
  disc3.1.tail-pad.48000hz_24bit_2ch.wav \
  disc3.2.tail-pad.48000hz_24bit_2ch.wav \

%.wav: %.pcm
	avconv -y -f s24le -ac 2 -ar 48k -i $< -acodec pcm_s24le $@

.PHONY: clean
clean:
	rm -fr *.wav *.pcm
