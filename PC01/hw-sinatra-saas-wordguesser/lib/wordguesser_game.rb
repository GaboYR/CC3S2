class WordGuesserGame
  attr_accessor :word, :guesses, :wrong_guesses

  def initialize(word)
    @word = word.downcase  # Convertir la palabra a minúsculas para hacerlo insensible a mayúsculas y minúsculas.
    @guesses = ''
    @wrong_guesses = ''
  end

  def guess(letter)
    unless letter =~ /^[a-z]$/i
      raise ArgumentError, 'Guess must be a single letter'
    end

    letter = letter.downcase  # Convertir la letra a minúsculas
    return false if @guesses.include?(letter) || @wrong_guesses.include?(letter)

    if @word.include?(letter)
      @guesses << letter
    else
      @wrong_guesses << letter
    end

    true
  end

  def word_with_guesses
    result = ''
    @word.chars.each do |letter|
      if @guesses.include?(letter)
        result << letter
      else
        result << '-'
      end
    end
    result
  end

  def check_win_or_lose
    return :win if word_guessed_correctly?
    return :lose if @wrong_guesses.length >= 7
    :play
  end

  private

  def word_guessed_correctly?
    @word.chars.all? { |letter| @guesses.include?(letter) }
  end
  def self.get_random_word
    require 'uri'
    require 'net/http'
    uri = URI('http://randomword.saasbook.info/RandomWord')
    Net::HTTP.new('randomword.saasbook.info').start { |http|
      return http.post(uri, "").body
    }
  end
end
