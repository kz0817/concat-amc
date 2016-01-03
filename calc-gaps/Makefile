all: \
  disc1.1.tail-pad.wav \
  disc1.2.tail-pad.wav \
  disc2.1.tail-pad.wav \
  disc2.2.tail-pad.wav \
  disc3.1.tail-pad.wav \
  disc3.2.tail-pad.wav

%.wav: %.pcm
	avconv -y -f s24le -ac 2 -ar 48k -i $< -acodec pcm_s24le $@
